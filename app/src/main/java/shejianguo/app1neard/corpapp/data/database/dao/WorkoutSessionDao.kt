package shejianguo.app1neard.corpapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import shejianguo.app1neard.corpapp.data.entity.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSessionDao {

    @Query("SELECT * FROM workout_sessions ORDER BY timestamp DESC")
    fun observeAll(): Flow<List<WorkoutSessionEntity>>

    @Insert
    suspend fun save(session: WorkoutSessionEntity): Long

    @Query("DELETE FROM workout_sessions")
    suspend fun deleteAll()
}
