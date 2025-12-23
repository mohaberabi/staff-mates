package com.erabigroupstaffmate.features.admin.staffdoc.navigation

import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import kotlinx.serialization.Serializable


@Serializable
data class StaffDocRoute(
    val staffDocRouteArgsJson: String
)

@Serializable
data class StaffDocRouteArgs(
    val year: String,
    val month: String,
    val payrollSummaryModel: PayrollSummaryModel
)