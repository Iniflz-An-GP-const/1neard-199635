package shejianguo.app1neard.corpapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shejianguo.app1neard.corpapp.data.repository.WorkoutRepository
import shejianguo.app1neard.corpapp.data.repository.WorkoutSessionRepository
import shejianguo.app1neard.corpapp.ui.state.DataUiState
import shejianguo.app1neard.corpapp.ui.state.SessionEntryUi
import shejianguo.app1neard.corpapp.ui.state.estimatedMinutes
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    /** Sessions newest first, each with the length of its workout. */
    val entriesState: StateFlow<DataUiState<List<SessionEntryUi>>> =
        workoutSessionRepository.observeAll()
            .map { sessions ->
                DataUiState.from(
                    sessions
                        .sortedByDescending { it.timestamp }
                        .map { session ->
                            SessionEntryUi(
                                session = session,
                                durationMinutes = workoutRepository.getById(session.workoutId)
                                    ?.estimatedMinutes() ?: 0,
                            )
                        }
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DataUiState.Initial,
            )
}
