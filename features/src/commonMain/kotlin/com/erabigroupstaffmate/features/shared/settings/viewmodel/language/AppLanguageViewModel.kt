package com.erabigroupstaffmate.features.shared.settings.viewmodel.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale.WriteAppLanguageUseCase
import com.erabigroupstaffmate.utility.localizations.AppLang
import kotlinx.coroutines.launch

class AppLanguageViewModel(
    private val writeAppLanguageUseCase: WriteAppLanguageUseCase,
) : ViewModel() {
    fun changeAppLanguage(lang: AppLang) {
        viewModelScope.launch { writeAppLanguageUseCase(lang) }
    }
}