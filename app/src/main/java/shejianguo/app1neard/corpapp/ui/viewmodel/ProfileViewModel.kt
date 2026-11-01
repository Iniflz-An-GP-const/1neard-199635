package shejianguo.app1neard.corpapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.data.repository.ProfileRepository
import shejianguo.app1neard.corpapp.data.repository.WorkoutRepository
import shejianguo.app1neard.corpapp.data.repository.WorkoutSessionRepository
import shejianguo.app1neard.corpapp.ui.state.activeDays
import shejianguo.app1neard.corpapp.ui.state.estimatedMinutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MAX_AGE_DIGITS = 3
private const val MAX_NAME_LENGTH = 30

data class ProfileForm(
    val name: String = "",
    val ageText: String = "",
    val fitnessLevel: Difficulty = Difficulty.BEGINNER,
)

data class ProfileStats(
    val workouts: Int = 0,
    val totalMinutes: Int = 0,
    val activeDays: Int = 0,
)

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    private val _saved = MutableStateFlow(ProfileForm())
    /** The profile as stored in DataStore. */
    val saved: StateFlow<ProfileForm> = _saved.asStateFlow()

    private val _draft = MutableStateFlow(ProfileForm())
    /** The profile as edited on screen; "Save" shows while it differs from [saved]. */
    val draft: StateFlow<ProfileForm> = _draft.asStateFlow()

    val stats: StateFlow<ProfileStats> =
        workoutSessionRepository.observeAll()
            .map { sessions ->
                ProfileStats(
                    workouts = sessions.size,
                    totalMinutes = sessions.sumOf { session ->
                        workoutRepository.getById(session.workoutId)?.estimatedMinutes() ?: 0
                    },
                    activeDays = sessions.activeDays(),
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ProfileStats(),
            )

    init {
        viewModelScope.launch {
            combine(
                profileRepository.observeName(),
                profileRepository.observeAge(),
                profileRepository.observeFitnessLevel(),
            ) { name, age, level ->
                ProfileForm(
                    name = name,
                    ageText = age?.toString().orEmpty(),
                    fitnessLevel = Difficulty.entries.firstOrNull { it.name == level } ?: Difficulty.BEGINNER,
                )
            }.collect { stored ->
                val wasUnchanged = _draft.value == _saved.value
                _saved.value = stored
                if (wasUnchanged) _draft.value = stored
            }
        }
    }

    fun onNameChanged(text: String) {
        if (text.length > MAX_NAME_LENGTH) return
        _draft.update { it.copy(name = text) }
    }

    fun onAgeChanged(text: String) {
        if (text.length > MAX_AGE_DIGITS || !text.all { it.isDigit() }) return
        _draft.update { it.copy(ageText = text) }
    }

    fun onFitnessLevelChanged(level: Difficulty) {
        _draft.update { it.copy(fitnessLevel = level) }
    }

    fun onSaveClick() {
        val form = _draft.value.copy(name = _draft.value.name.trim())
        _draft.value = form
        _saved.value = form
        viewModelScope.launch {
            profileRepository.setProfile(
                name = form.name,
                age = form.ageText.toIntOrNull(),
                fitnessLevel = form.fitnessLevel.name,
            )
        }
    }
}
