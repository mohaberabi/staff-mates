package com.erabigroupstaffmate.utility.validator.email

import com.erabigroupstaffmate.utility.regex.EMAIL_ADDRESS_REGEX

class DefaultEmailAddressValidator : EmailAddressValidator {
    override fun isValidEmail(
        email: String,
    ): Boolean = email.matches(Regex(EMAIL_ADDRESS_REGEX))
}