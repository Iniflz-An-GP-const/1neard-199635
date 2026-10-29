package shejianguo.app1neard.corpapp.data.repository

import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.data.model.ExerciseModel
import shejianguo.app1neard.corpapp.data.model.Intensity
import shejianguo.app1neard.corpapp.data.model.WorkoutModel
import shejianguo.app1neard.corpapp.data.model.WorkoutType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WorkoutRepository {
    private val workouts = listOf<WorkoutModel>(

    )

    fun observeAll(): Flow<List<WorkoutModel>> {
        return flowOf(workouts)
    }

    fun observeById(id: Int): Flow<WorkoutModel?> {
        val service = workouts.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): WorkoutModel? {
        return workouts.firstOrNull { service -> service.id == id }
    }
}
