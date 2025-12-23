package com.erabigroupstaffmate.utility.utils.extensions.viewcontroller


import platform.UIKit.UIApplication
import platform.UIKit.UIViewController


fun getRootViewController(): UIViewController {
    val window = UIApplication.sharedApplication.keyWindow
    return window?.rootViewController
        ?: throw IllegalStateException("Unable to find root view controller")
}