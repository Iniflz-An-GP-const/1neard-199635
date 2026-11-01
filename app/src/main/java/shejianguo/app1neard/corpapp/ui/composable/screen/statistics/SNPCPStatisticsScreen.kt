package shejianguo.app1neard.corpapp.ui.composable.screen.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.WorkoutType
import shejianguo.app1neard.corpapp.ui.composable.approot.LocalContentBottomPadding
import shejianguo.app1neard.corpapp.ui.composable.screen.statistics.chart.WeeklyBarChart
import shejianguo.app1neard.corpapp.ui.composable.screen.statistics.summary.MotivationCard
import shejianguo.app1neard.corpapp.ui.composable.screen.statistics.summary.StatCard
import shejianguo.app1neard.corpapp.ui.state.SessionEntryUi
import shejianguo.app1neard.corpapp.ui.state.currentWeekCounts
import shejianguo.app1neard.corpapp.ui.state.formatHours
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.viewmodel.StatisticsViewModel
import org.koin.androidx.compose.koinViewModel

private data class StatValue(val value: String, val label: String)

@Composable
fun SNPCPStatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = koinViewModel(),
) {
    val entries by viewModel.entries.collectAsStateWithLifecycle()

    StatisticsContent(
        entries = entries,
        modifier = modifier,
    )
}

@Composable
private fun StatisticsContent(
    entries: List<SessionEntryUi>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val sessions = remember(entries) { entries.map { it.session } }

    val weekCounts = remember(sessions) { sessions.currentWeekCounts() }
    val activeDaysThisWeek = weekCounts.count { it > 0 }
    val totalMinutes = entries.sumOf { it.durationMinutes }
    val favoriteType = remember(sessions) {
        sessions.groupingBy { it.workoutType }.eachCount().maxByOrNull { it.value }?.key
            ?.let { name -> WorkoutType.entries.firstOrNull { it.name == name } }
    }

    val stats = listOf(
        StatValue(
            value = sessions.size.toString(),
            label = stringResource(R.string.statistics_total_workouts),
        ),
        StatValue(
            value = formatHours(totalMinutes),
            label = stringResource(R.string.statistics_total_hours),
        ),
        StatValue(
            value = favoriteType?.let { stringResource(it.titleRes) }
                ?: stringResource(R.string.statistics_no_favorite),
            label = stringResource(R.string.statistics_favorite_type),
        ),
        StatValue(
            value = stringResource(R.string.statistics_weekly_activity_format, activeDaysThisWeek),
            label = stringResource(R.string.statistics_weekly_activity),
        ),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = dimens.screenPadding,
            end = dimens.screenPadding,
            top = dimens.screenPadding,
            bottom = dimens.screenPadding + LocalContentBottomPadding.current,
        ),
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        items(stats) { stat ->
            StatCard(value = stat.value, label = stat.label)
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            WeeklyBarChart(
                counts = weekCounts,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            MotivationCard(
                totalWorkouts = sessions.size,
                activeDaysThisWeek = activeDaysThisWeek,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
