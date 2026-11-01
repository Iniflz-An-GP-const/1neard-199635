package shejianguo.app1neard.corpapp.ui.composable.screen.home.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.data.model.WorkoutType
import shejianguo.app1neard.corpapp.ui.composable.screen.home.filter.content.FilterChipContent
import shejianguo.app1neard.corpapp.ui.composable.screen.home.filter.shell.WorkoutFilterRowShell
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

/** Two single-select chip rows: the workout type, then the difficulty. */
@Composable
fun WorkoutFilterRow(
    selectedType: WorkoutType?,
    selectedDifficulty: Difficulty?,
    onTypeSelected: (WorkoutType?) -> Unit,
    onDifficultySelected: (Difficulty?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val allLabel = stringResource(R.string.snpcp_workout_filter_all)

    val typeItems = listOf(
        FilterChipItem(
            label = allLabel,
            selected = selectedType == null,
            onClick = { onTypeSelected(null) },
        ),
    ) + WorkoutType.entries.map { type ->
        FilterChipItem(
            label = stringResource(type.titleRes),
            selected = selectedType == type,
            onClick = { onTypeSelected(if (selectedType == type) null else type) },
            icon = type.icon,
        )
    }

    val difficultyItems = Difficulty.entries.map { difficulty ->
        FilterChipItem(
            label = stringResource(difficulty.titleRes),
            selected = selectedDifficulty == difficulty,
            onClick = { onDifficultySelected(if (selectedDifficulty == difficulty) null else difficulty) },
            level = difficulty.level,
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
    ) {
        WorkoutFilterRowShell(items = typeItems) { item -> FilterChipContent(item) }

        WorkoutFilterRowShell(items = difficultyItems) { item -> FilterChipContent(item) }
    }
}
