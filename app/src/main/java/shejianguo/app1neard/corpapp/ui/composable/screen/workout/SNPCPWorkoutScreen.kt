package shejianguo.app1neard.corpapp.ui.composable.screen.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.WorkoutModel
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.DataBasedContainer
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.DataEmptyContent
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.LabelChip
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.WorkoutImage
import shejianguo.app1neard.corpapp.ui.composable.screen.workout.actionblock.WorkoutActionBlock
import shejianguo.app1neard.corpapp.ui.composable.screen.workout.exerciserow.ExerciseRow
import shejianguo.app1neard.corpapp.ui.state.DataUiState
import shejianguo.app1neard.corpapp.ui.state.WorkoutUiState
import shejianguo.app1neard.corpapp.ui.state.estimatedMinutes
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.viewmodel.WorkoutViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private val HeroHeight = 260.dp
private val HeroCorner = 28.dp
private val NameSize = 22.sp
private val InfoIconSize = 18.dp

@Composable
fun SNPCPWorkoutScreen(
    workoutId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkoutViewModel = koinViewModel { parametersOf(workoutId) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DataBasedContainer(
        modifier = modifier.fillMaxSize(),
        dataState = uiState,

        dataPopulated = {
            val state = (uiState as DataUiState.Populated<WorkoutUiState>).data

            WorkoutScreenContent(
                workout = state.workout,
                onCompletionConfirmed = { viewModel.onMarkCompleted(onSaved = onNavigateBack) },
            )
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.workout_details_state_empty),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun WorkoutScreenContent(
    workout: WorkoutModel,
    onCompletionConfirmed: () -> Unit,
) {
    var showCompleteDialog by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }

    WorkoutContent(
        workout = workout,
        onStartClick = { showCompleteDialog = true },
    )

    if (showCompleteDialog) {
        MarkCompletedDialog(
            onConfirm = {
                showCompleteDialog = false
                if (!isSaving) {
                    isSaving = true
                    onCompletionConfirmed()
                }
            },
            onDismiss = { showCompleteDialog = false },
        )
    }
}

@Composable
private fun WorkoutContent(
    workout: WorkoutModel,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = dimens.screenPadding),
        ) {
            item {
                WorkoutImage(
                    imageUrl = workout.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HeroHeight)
                        .clip(RoundedCornerShape(bottomStart = HeroCorner, bottomEnd = HeroCorner)),
                )
            }

            item {
                WorkoutHeader(
                    workout = workout,
                    modifier = Modifier.padding(
                        start = dimens.screenPadding,
                        end = dimens.screenPadding,
                        top = dimens.contentSpacing,
                        bottom = dimens.contentSpacing,
                    ),
                )
            }

            itemsIndexed(workout.exercises) { index, exercise ->
                ExerciseRow(
                    number = index + 1,
                    exercise = exercise,
                    isAlternate = index % 2 == 1,
                )
            }
        }

        WorkoutActionBlock(
            isInProgress = false,
            onClick = onStartClick,
        )
    }
}

@Composable
private fun WorkoutHeader(
    workout: WorkoutModel,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = workout.name,
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = NameSize, lineHeight = 28.sp),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Row(horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing)) {
            LabelChip(text = stringResource(workout.type.titleRes), icon = workout.type.icon)

            LabelChip(
                text = stringResource(workout.difficulty.titleRes),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }

        Spacer(modifier = Modifier.height(dimens.itemSpacing))

        Row(horizontalArrangement = Arrangement.spacedBy(dimens.sectionGap)) {
            InfoItem(
                icon = Icons.Filled.Timer,
                text = stringResource(R.string.workout_info_duration, workout.estimatedMinutes()),
            )

            InfoItem(
                icon = Icons.Filled.FormatListNumbered,
                text = stringResource(R.string.workout_card_exercise_count, workout.exercises.size),
            )
        }
    }
}

@Composable
private fun InfoItem(
    icon: ImageVector,
    text: String,
) {
    val dimens = LocalDimens.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(InfoIconSize),
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun MarkCompletedDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.mark_completed_dialog_title)) },
        text = { Text(stringResource(R.string.mark_completed_dialog_message)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.mark_completed_dialog_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.exercise_dialog_cancel))
            }
        },
    )
}
