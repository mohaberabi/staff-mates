package com.erabigroupstaffmate.uihub.components.date

import com.erabigroupstaffmate.erabitime.domain.constants.arabicMonthNames
import com.erabigroupstaffmate.utility.localizations.AppLang
import kotlinx.datetime.format.MonthNames

class WheelPickerMonthState(
    val monthNameEn: String = MonthNames.ENGLISH_FULL.names.first(),
    val monthNameAr: String = arabicMonthNames.names.first(),
    val monthNumber: Int = 1,
) {
    companion object {
        fun fromMonth(month: Int): WheelPickerMonthState {

            if (month > 12 || month < 0) {
                return WheelPickerMonthState()
            }


            return WheelPickerMonthState(
                monthNumber = month,
                monthNameEn = MonthNames.ENGLISH_FULL.names[month - 1],
                monthNameAr = arabicMonthNames.names[month - 1]
            )

        }
    }
}

fun WheelPickerMonthState.localizeMonth(lang: AppLang) =
    if (lang == AppLang.Arabic) monthNameAr else monthNameEn



