package shejianguo.app1neard.corpapp.ui.composable.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.composable.approot.LocalContentBottomPadding
import shejianguo.app1neard.corpapp.ui.composable.screen.history.card.HistorySummaryCard
import shejianguo.app1neard.corpapp.ui.composable.screen.history.card.WorkoutSessionCard
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.DataEmptyContent
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.listmotion.LocalPlayedEntrances
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.listmotion.listEntrance
import shejianguo.app1neard.corpapp.ui.state.DataUiState
import shejianguo.app1neard.corpapp.ui.state.SessionEntryUi
import shejianguo.app1neard.corpapp.ui.state.currentStreak
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.viewmodel.HistoryViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun SNPCPHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = koinViewModel(),
) {
    val entriesState by viewModel.entriesState.collectAsStateWithLifecycle()

    when (val state = entriesState) {
        DataUiState.Initial -> Unit

        DataUiState.Empty -> DataEmptyContent(
            primaryText = stringResource(R.string.history_empty),
            modifier = modifier.fillMaxSize(),
        )

        is DataUiState.Populated -> HistoryContent(
            entries = state.data,
            modifier = modifier,
        )
    }
}

@Composable
private fun HistoryContent(
    entries: List<SessionEntryUi>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    val playedEntrances = remember { mutableSetOf<Any>() }
    val groups = remember(entries) {
        entries.groupBy { it.session.timestamp.toLocalDate() }.toSortedMap(compareByDescending { it })
    }
    val streak = remember(entries) { entries.map { it.session }.currentStreak() }

    CompositionLocalProvider(LocalPlayedEntrances provides playedEntrances) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
            verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        ) {
            item {
                HistorySummaryCard(
                    totalWorkouts = entries.size,
                    streak = streak,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            groups.forEach { (date, dayEntries) ->
                item(key = "header-$date") {
                    Text(
                        text = friendlyDate(date),
                        modifier = Modifier.padding(top = dimens.itemSpacing),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                items(dayEntries, key = { it.session.id }) { entry ->
                    WorkoutSessionCard(
                        entry = entry,
                        modifier = Modifier.listEntrance(key = entry.session.id, index = 0),
                    )
                }
            }
        }
    }
}

@Composable
private fun friendlyDate(date: LocalDate): String {
    val today = LocalDate.now()
    return when (date) {
        today -> stringResource(R.string.history_date_today)
        today.minusDays(1) -> stringResource(R.string.history_date_yesterday)
        else -> date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }
}
