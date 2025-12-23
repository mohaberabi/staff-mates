package com.erabigroupstaffmate.uihub.components.inject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.erabigroupstaffmate.erabitime.domain.ErabiDateFormatter
import org.koin.compose.currentKoinScope


@Composable
fun rememberDateFormatter(): ErabiDateFormatter {
    val koinScope = currentKoinScope()
    return remember {
        koinScope.get<ErabiDateFormatter>()
    }
}