package com.erabigroupstaffmate.utility.filelauncher

interface InternalFileLauncher {

    fun launch(
        absoluteFilePath: String,
        mimeType: String = "application/pdf"
    )
}