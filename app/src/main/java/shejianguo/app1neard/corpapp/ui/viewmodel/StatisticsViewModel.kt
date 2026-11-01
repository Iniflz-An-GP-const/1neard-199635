package shejianguo.app1neard.corpapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shejianguo.app1neard.corpapp.data.repository.WorkoutRepository
import shejianguo.app1neard.corpapp.data.repository.WorkoutSessionRepository
import shejianguo.app1neard.corpapp.ui.state.SessionEntryUi
import shejianguo.app1neard.corpapp.ui.state.estimatedMinutes
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StatisticsViewModel(
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    /** Every saved session with the length of its workout; empty until the first workout. */
    val entries: StateFlow<List<SessionEntryUi>> =
        workoutSessionRepository.observeAll()
            .map { sessions ->
                sessions.map { session ->
                    SessionEntryUi(
                        session = session,
                        durationMinutes = workoutRepository.getById(session.workoutId)
                            ?.estimatedMinutes() ?: 0,
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList(),
            )
}
