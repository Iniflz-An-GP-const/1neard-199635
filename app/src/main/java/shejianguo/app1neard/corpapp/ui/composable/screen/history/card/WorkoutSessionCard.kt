package shejianguo.app1neard.corpapp.ui.composable.screen.history.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.entity.WorkoutSessionEntity
import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.data.model.WorkoutType
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.theme.cardBorderStroke
import java.time.format.DateTimeFormatter
import java.util.Locale

private val IconBadgeSize = 40.dp
private val IconSize = 20.dp
private val ProgressBarHeight = 4.dp
private val TimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)

@Composable
fun WorkoutSessionCard(
    session: WorkoutSessionEntity,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val type = remember(session.workoutType) { WorkoutType.valueOf(session.workoutType) }
    val difficulty = remember(session.difficulty) { Difficulty.valueOf(session.difficulty) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier
                .padding(dimens.cardPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(IconBadgeSize)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = type.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(IconSize),
                )
            }

            Spacer(modifier = Modifier.width(dimens.itemSpacing))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = session.workoutName,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.width(dimens.itemSpacing))

                    Text(
                        text = session.timestamp.format(TimeFormatter),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Spacer(modifier = Modifier.height(dimens.tightSpacing))

                Text(
                    text = stringResource(
                        R.string.workout_header_subtitle,
                        stringResource(type.titleRes),
                        stringResource(difficulty.titleRes),
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                if (!session.isFullyCompleted) {
                    Spacer(modifier = Modifier.height(dimens.tightSpacing))

                    LinearProgressIndicator(
                        progress = { session.completedExercises / session.totalExercises.toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(ProgressBarHeight)
                            .clip(RoundedCornerShape(ProgressBarHeight / 2)),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                    )
                }
            }

            if (session.isFullyCompleted) {
                Spacer(modifier = Modifier.width(dimens.itemSpacing))

                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
