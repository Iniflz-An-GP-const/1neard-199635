package shejianguo.app1neard.corpapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shejianguo.app1neard.corpapp.data.repository.WorkoutSessionRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val workoutSessionRepository: WorkoutSessionRepository,
) : ViewModel() {

    fun onResetDataConfirmed() {
        viewModelScope.launch {
            workoutSessionRepository.deleteAll()
        }
    }
}
