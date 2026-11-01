package shejianguo.app1neard.corpapp.ui.composable.screen.statistics.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private const val ActiveWeekDays = 3

@Composable
fun MotivationCard(
    totalWorkouts: Int,
    activeDaysThisWeek: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val messageRes = when {
        totalWorkouts == 0 -> R.string.statistics_motivation_start
        activeDaysThisWeek >= ActiveWeekDays -> R.string.statistics_motivation_strong
        else -> R.string.statistics_motivation_progress
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        Column(modifier = Modifier.padding(dimens.cardPadding)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = Icons.Filled.LocalFireDepartment, contentDescription = null)

                Text(
                    text = stringResource(R.string.statistics_motivation_title),
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            Text(
                text = stringResource(messageRes),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
