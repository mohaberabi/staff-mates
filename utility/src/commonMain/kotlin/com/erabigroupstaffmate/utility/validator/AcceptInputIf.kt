package com.erabigroupstaffmate.utility.validator


fun interface AcceptInputIf {
    operator fun invoke(onChanged: (input: String) -> Unit): (input: String) -> Unit
}


val AcceptInputIfDecimal = AcceptInputIf { onChanged ->
    { input ->
        val regex = Regex("^\\d*(\\.\\d*)?$")
        if (regex.matches(input)) {
            onChanged(input)
        }
    }

}


val AcceptInputIfNumber = AcceptInputIf { onChanged ->
    { input ->
        if (input.all { it.isDigit() }) {
            onChanged(input)
        } else {
            onChanged("")
        }
    }

}