package com.erabigroupstaffmate.preferences.domain

sealed class PreferencesKey(val key: String) {
    data object BusinessDay : PreferencesKey("business_day")
    data object DeviceSettings : PreferencesKey("device_settings")
    data object AppMode : PreferencesKey("app_mode")
    data object AppLocale : PreferencesKey("app_locale")

    data object WorkingHours : PreferencesKey("working_hours")
    data object UserData : PreferencesKey("user_data")

}