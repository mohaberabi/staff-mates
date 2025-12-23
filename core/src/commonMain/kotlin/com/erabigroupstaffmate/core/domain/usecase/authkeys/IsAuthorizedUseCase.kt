package com.erabigroupstaffmate.core.domain.usecase.authkeys

import com.erabigroupstaffmate.modelhub.AuthRole
import com.erabigroupstaffmate.core.domain.repository.AuthKeyRepository

class IsAuthorizedUseCase(
    private val authKeyRepository: AuthKeyRepository,
) {

    suspend operator fun invoke(code: String, key: AuthRole): Boolean {
        val authKey = authKeyRepository.getAuthKey(key = code) ?: return false
        return authKey.isAdmin || authKey.roles.contains(key.type)
    }
}