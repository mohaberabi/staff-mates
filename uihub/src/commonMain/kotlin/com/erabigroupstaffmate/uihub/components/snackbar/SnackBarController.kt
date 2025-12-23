package com.erabigroupstaffmate.uihub.components.snackbar

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow


interface SnackBarController {
    suspend fun send(message: SnackbarMessage)
    fun collect(): Flow<SnackbarMessage>
}


fun provideSnackBarController(): SnackBarController = DefaultSnackBarController()
internal class DefaultSnackBarController : SnackBarController {
    private val channel = Channel<SnackbarMessage>()
    override fun collect(): Flow<SnackbarMessage> = channel.receiveAsFlow()
    override suspend fun send(message: SnackbarMessage) {
        channel.send(message)
    }

}