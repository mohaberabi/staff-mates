package com.erabigroupstaffmate.uihub.components.common.numpad

sealed interface NumPadMember {
    data class Digit(val value: Char) : NumPadMember
    data object ClearLast : NumPadMember
    data object ClearAll : NumPadMember
}
