package com.erabigroupstaffmate.uihub.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.uihub.components.NetworkImage
import com.erabigroupstaffmate.uihub.components.design.AppListItem
import com.erabigroupstaffmate.uihub.components.design.PrimaryDivider
import com.erabigroupstaffmate.uihub.components.sheets.AppBottomSheet
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.add_borrow
import com.erabigroupstaffmate.uihub.resources.add_deduct
import com.erabigroupstaffmate.uihub.resources.borrow
import com.erabigroupstaffmate.uihub.resources.deduct


sealed interface StaffClickActions {
    data class AddDeduct(val staff: StaffModel) : StaffClickActions
    data class AddBorrow(val staff: StaffModel) : StaffClickActions
    data class Info(val staff: StaffModel) : StaffClickActions
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffCard(
    modifier: Modifier = Modifier,
    isClickable: Boolean = true,
    onActions: (StaffClickActions) -> Unit = {},
    staff: StaffModel,
) {
    var showActions by remember {
        mutableStateOf(false)
    }

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable(
                    enabled = isClickable,
                    onClick = { showActions = true },
                ),
        ) {
            Box(
                Modifier
                    .size(65.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                NetworkImage(
                    image = staff.profilePicUrl,
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(Modifier.width(8.dp))
            Column {
                Text(
                    staff.fullName,
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    staff.title,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge
                        .copy(fontWeight = FontWeight.SemiBold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "${staff.chainName} ( ${staff.branchName} )",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        PrimaryDivider()
    }
    if (showActions) {
        AppBottomSheet(
            onDismissRequest = { showActions = false },
        ) {
            AppListItem(
                icon = Res.drawable.deduct,
                leading = Res.string.add_deduct,
                onClick = { onActions(StaffClickActions.AddDeduct(staff)) },
            )
            AppListItem(
                leading = Res.string.add_borrow,
                icon = Res.drawable.borrow,
                onClick = { onActions(StaffClickActions.AddBorrow(staff)) },
            )
        }
    }
}

@Composable
fun SimpleStaffCard(
    modifier: Modifier = Modifier,
    staffName: String,
    subtitle: String,
    image: String? = null
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    )
    {
        image?.let {
            Box(
                Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                NetworkImage(
                    image = it,
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Column {
            Text(
                staffName,
                style = MaterialTheme.typography.titleMedium
                    .copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                subtitle,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge
                    .copy(fontWeight = FontWeight.SemiBold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}