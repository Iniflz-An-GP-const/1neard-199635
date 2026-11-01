package shejianguo.app1neard.corpapp.ui.composable.screen.home.workoutcard.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.composable.screen.home.workoutcard.WorkoutCardInfo
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.LabelChip
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private val NameSize = 16.sp

@Composable
fun WorkoutCardContent(info: WorkoutCardInfo, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(modifier = modifier.padding(horizontal = dimens.cardPadding)) {
        Text(
            text = info.name,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = NameSize),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LabelChip(text = info.typeLabel, icon = info.typeIcon)

            LabelChip(
                text = info.difficultyLabel,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Text(
            text = stringResource(R.string.workout_card_meta, info.durationMinutes, info.exerciseCount),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
