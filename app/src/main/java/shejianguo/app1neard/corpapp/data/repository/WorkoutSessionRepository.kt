package shejianguo.app1neard.corpapp.data.repository

import shejianguo.app1neard.corpapp.data.database.dao.WorkoutSessionDao
import shejianguo.app1neard.corpapp.data.entity.WorkoutSessionEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WorkoutSessionRepository(
    private val workoutSessionDao: WorkoutSessionDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend fun save(session: WorkoutSessionEntity): Long {
        return withContext(coroutineDispatcher) {
            workoutSessionDao.save(session)
        }
    }

    fun observeAll(): Flow<List<WorkoutSessionEntity>> {
        return workoutSessionDao.observeAll()
    }

    suspend fun deleteAll() {
        withContext(coroutineDispatcher) {
            workoutSessionDao.deleteAll()
        }
    }
}
