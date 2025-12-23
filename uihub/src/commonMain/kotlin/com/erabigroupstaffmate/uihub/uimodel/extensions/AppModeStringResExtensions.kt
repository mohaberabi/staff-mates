package com.erabigroupstaffmate.uihub.uimodel.extensions

import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.admin_mode_subtitle
import com.erabigroupstaffmate.uihub.resources.admin_mode_title
import com.erabigroupstaffmate.uihub.resources.kiosk_mode_subtitle
import com.erabigroupstaffmate.uihub.resources.kiosk_mode_title


fun AppMode.uiInfo() = when (this) {
    AppMode.Kiosk -> Res.string.kiosk_mode_title to Res.string.kiosk_mode_subtitle
    AppMode.Admin -> Res.string.admin_mode_title to Res.string.admin_mode_subtitle
    AppMode.Unknown -> Res.string.kiosk_mode_title to Res.string.kiosk_mode_subtitle
}