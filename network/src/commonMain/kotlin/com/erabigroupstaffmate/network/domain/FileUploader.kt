package com.erabigroupstaffmate.network.domain

interface FileUploader {


    suspend fun uploadAndGetUrl(
        fileAbsolutePath: String,
        reference: String,
    ): String
}