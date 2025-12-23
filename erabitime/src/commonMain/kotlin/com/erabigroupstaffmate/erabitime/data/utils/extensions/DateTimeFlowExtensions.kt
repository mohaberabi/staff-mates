package com.erabigroupstaffmate.erabitime.data.utils.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.datetime.LocalDateTime


fun Flow<LocalDateTime>.distinctByYearMonth() = distinctUntilChanged { first, second ->
    first.year == second.year && first.monthNumber == second.monthNumber
}