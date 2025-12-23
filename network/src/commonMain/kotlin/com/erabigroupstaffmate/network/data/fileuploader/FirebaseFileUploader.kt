package com.erabigroupstaffmate.network.data.fileuploader

import com.erabigroupstaffmate.network.domain.FileUploader
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import dev.gitlive.firebase.storage.FirebaseStorage
import kotlinx.coroutines.withContext

class FirebaseFileUploader(
    private val firebaseStorage: FirebaseStorage,
    private val firebaseFileProvider: FirebaseFileProvider,
    private val dispatchers: DispatchersProvider,
) : FileUploader {


    override suspend fun uploadAndGetUrl(
        fileAbsolutePath: String,
        reference: String,
    ): String = withContext(dispatchers.io) {

        val file = firebaseFileProvider.provideFile(fileAbsolutePath)
        firebaseStorage.reference.child(reference).putFile(file)
        return@withContext firebaseStorage.reference(reference).getDownloadUrl()
    }
}