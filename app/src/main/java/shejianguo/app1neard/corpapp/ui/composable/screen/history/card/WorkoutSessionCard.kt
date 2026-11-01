package shejianguo.app1neard.corpapp.ui.composable.screen.history.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.WorkoutType
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.LabelChip
import shejianguo.app1neard.corpapp.ui.state.SessionEntryUi
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.theme.cardBorderStroke
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun WorkoutSessionCard(
    entry: SessionEntryUi,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val session = entry.session
    val type = remember(session.workoutType) {
        WorkoutType.entries.firstOrNull { it.name == session.workoutType }
    }
    val timeFormatter = remember { DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(
            modifier = Modifier
                .padding(dimens.cardPadding)
                .fillMaxWidth(),
        ) {
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
                    text = session.timestamp.format(timeFormatter),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            Row(
                horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (type != null) {
                    LabelChip(text = stringResource(type.titleRes), icon = type.icon)
                }

                Text(
                    text = stringResource(R.string.workout_info_duration, entry.durationMinutes),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
