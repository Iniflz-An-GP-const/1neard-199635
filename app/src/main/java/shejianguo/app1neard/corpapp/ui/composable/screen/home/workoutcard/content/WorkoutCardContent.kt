package shejianguo.app1neard.corpapp.ui.composable.screen.home.workoutcard.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.ui.composable.screen.home.workoutcard.WorkoutCardInfo
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private val TypeIconSize = 16.dp
private val LevelBarWidth = 4.dp
private val LevelBarHeight = 12.dp
private val LevelBarSpacing = 2.dp
private const val LevelBarUnfilledAlpha = 0.3f

@Composable
fun WorkoutCardContent(info: WorkoutCardInfo, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(modifier = modifier.padding(horizontal = dimens.cardPadding)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = info.typeIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(TypeIconSize),
            )

            Text(
                text = info.name,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Spacer(modifier = Modifier.height(dimens.contentSpacing))

        Row(
            horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DifficultyLevelBars(level = info.difficultyLevel)

            Text(
                text = stringResource(R.string.workout_card_exercise_count, info.exerciseCount),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun DifficultyLevelBars(
    level: Int,
    modifier: Modifier = Modifier,
    maxLevel: Int = Difficulty.entries.size,
) {
    val color = MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(LevelBarSpacing),
    ) {
        repeat(maxLevel) { index ->
            Box(
                modifier = Modifier
                    .size(width = LevelBarWidth, height = LevelBarHeight)
                    .background(
                        color = if (index < level) color else color.copy(alpha = LevelBarUnfilledAlpha),
                        shape = RoundedCornerShape(LevelBarWidth / 2),
                    ),
            )
        }
    }
}
