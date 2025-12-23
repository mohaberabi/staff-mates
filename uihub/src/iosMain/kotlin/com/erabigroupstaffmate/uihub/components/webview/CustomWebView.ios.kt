package com.erabigroupstaffmate.uihub.components.webview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.CValue
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.QuartzCore.CATransaction
import platform.QuartzCore.kCATransactionDisableActions
import platform.UIKit.UIView
import platform.WebKit.WKNavigation
import platform.WebKit.WKNavigationAction
import platform.WebKit.WKNavigationActionPolicy
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.darwin.NSObject
import kotlinx.cinterop.ExperimentalForeignApi
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import platform.Foundation.NSURLComponents
import platform.Foundation.NSURLQueryItem

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun CustomWebView(
    modifier: Modifier,
    initialUrl: String,
    onLoaded: (String, QueryParamsMap) -> Unit,
    onLoading: (String, QueryParamsMap) -> Unit,
    isLoading: (Boolean) -> Unit
) {
    val webView = remember {
        WKWebView(
            frame = CGRectZero.readValue(),
        )
    }

    val delegate = rememberWebViewDelegate(
        onLoaded = onLoaded,
        onLoading = onLoading,
        isLoading = isLoading,
    )
    UIKitView(
        factory = {
            webView.navigationDelegate = delegate
            webView.apply {
                WKWebViewConfiguration().apply {
                    allowsInlineMediaPlayback = true
                    allowsAirPlayForMediaPlayback = true
                    allowsPictureInPictureMediaPlayback = true
                }
            }
            webView.loadRequest(NSURLRequest.requestWithURL(NSURL.URLWithString(initialUrl)!!))
            webView
        },
        modifier = modifier.fillMaxSize(),
        update = {
            { view: UIView, rect: CValue<CGRect> ->
                CATransaction.begin()
                CATransaction.setValue(true, kCATransactionDisableActions)
                view.layer.setFrame(rect)
                webView.setFrame(rect)
                CATransaction.commit()
            }
        },
        properties = UIKitInteropProperties(
            isInteractive = true,
            isNativeAccessibilityEnabled = true
        )
    )
}

class WebViewDelegate(
    private val onLoaded: (String, QueryParamsMap) -> Unit,
    private val onLoading: (String, QueryParamsMap) -> Unit,
    private val isLoading: (Boolean) -> Unit,
) : NSObject(), WKNavigationDelegateProtocol {
    override fun webView(webView: WKWebView, didFinishNavigation: WKNavigation?) {
        val current = webView.URL?.absoluteString
        current?.let {
            onLoaded(it, it.toQueryParams())

        }
        isLoading(false)
    }

    override fun webView(
        webView: WKWebView,
        decidePolicyForNavigationAction: WKNavigationAction,
        decisionHandler: (WKNavigationActionPolicy) -> Unit
    ) {
        isLoading(true)
        val currentUrl =
            decidePolicyForNavigationAction.request.URL?.absoluteString ?: ""
        decisionHandler(WKNavigationActionPolicy.WKNavigationActionPolicyAllow)
        onLoading(currentUrl, currentUrl.toQueryParams())
    }
}

@Composable
fun rememberWebViewDelegate(
    onLoaded: (String, QueryParamsMap) -> Unit,
    onLoading: (String, QueryParamsMap) -> Unit,
    isLoading: (Boolean) -> Unit,
): WebViewDelegate {
    return remember {
        WebViewDelegate(
            onLoaded = onLoaded,
            onLoading = onLoading,
            isLoading = isLoading
        )
    }
}

actual fun String.toQueryParams(): QueryParamsMap {
    val urlComponents = NSURLComponents.componentsWithString(this)
    val queryItems =
        urlComponents?.queryItems?.map { it as? NSURLQueryItem } ?: emptyList()
    val queryParams =
        queryItems.mapNotNull { it }.associate { it.name to it.value }
    return queryParams
}