package com.erabigroupstaffmate.utility.disptachersprovider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

interface DispatchersProvider {


    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher


}


