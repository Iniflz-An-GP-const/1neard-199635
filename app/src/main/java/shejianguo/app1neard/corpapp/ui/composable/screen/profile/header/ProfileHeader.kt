package shejianguo.app1neard.corpapp.ui.composable.screen.profile.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.LabelChip
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.theme.cardBorderStroke

private val AvatarSize = 80.dp
private val NameSize = 20.sp

@Composable
fun ProfileHeader(
    name: String,
    fitnessLevelLabel: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(AvatarSize)
                .background(MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = initialsOf(name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = NameSize),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        LabelChip(text = fitnessLevelLabel)
    }
}

@Composable
fun ProfileStatsRow(
    workouts: String,
    hours: String,
    activeDays: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        MiniStatCard(workouts, stringResource(R.string.profile_stat_workouts), Modifier.weight(1f))
        MiniStatCard(hours, stringResource(R.string.profile_stat_hours), Modifier.weight(1f))
        MiniStatCard(activeDays, stringResource(R.string.profile_stat_days_active), Modifier.weight(1f))
    }
}

@Composable
private fun MiniStatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimens.itemSpacing, horizontal = dimens.tightSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
            )

            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

private fun initialsOf(name: String): String {
    return name.trim()
        .split(Regex("\\s+"))
        .filter { it.isNotEmpty() }
        .take(2)
        .joinToString("") { it.first().uppercase() }
}
