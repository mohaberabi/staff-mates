package com.erabigroupstaffmate.utility.localizations

import com.erabigroupstaffmate.utility.filelauncher.InternalFileLauncher
import com.erabigroupstaffmate.utility.utils.extensions.viewcontroller.getRootViewController
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.UIKit.UIDocumentInteractionController
import platform.UIKit.UIDocumentInteractionControllerDelegateProtocol
import platform.UIKit.UIViewController
import platform.darwin.NSObject


class FileLauncherDelegate : NSObject(), UIDocumentInteractionControllerDelegateProtocol {
    override fun documentInteractionControllerViewControllerForPreview(
        controller: UIDocumentInteractionController
    ): UIViewController = getRootViewController()

}

class IosInternalFileLauncher : InternalFileLauncher {

    private val docDelegate = FileLauncherDelegate()
    private var docController: UIDocumentInteractionController? = null

    override fun launch(absoluteFilePath: String, mimeType: String) {
        val fileUrl = NSURL.fileURLWithPath(absoluteFilePath)
        if (!NSFileManager.defaultManager.fileExistsAtPath(absoluteFilePath)) {
            println("File is not exist at $absoluteFilePath")
            return
        }
        val controller = UIDocumentInteractionController().apply {
            setURL(fileUrl)
            setDelegate(docDelegate)
        }
        controller.presentPreviewAnimated(true)
        docController = controller

    }
}