package com.erabigroupstaffmate.network.data.fileuploader

import dev.gitlive.firebase.storage.File


expect class FirebaseFileProvider {
    fun provideFile(platformPath: String): File
}