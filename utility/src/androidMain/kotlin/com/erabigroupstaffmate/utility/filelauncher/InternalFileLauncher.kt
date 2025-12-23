package com.erabigroupstaffmate.utility.filelauncher

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File


class AndroidInternalFileLauncher(
    private val context: Context
) : InternalFileLauncher {
    override fun launch(
        absoluteFilePath: String,
        mimeType: String
    ) {
        val file = File(absoluteFilePath)
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, mimeType)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)

    }
}