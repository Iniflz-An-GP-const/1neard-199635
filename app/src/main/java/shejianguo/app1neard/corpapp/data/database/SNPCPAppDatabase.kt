package shejianguo.app1neard.corpapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shejianguo.app1neard.corpapp.data.database.converter.Converters
import shejianguo.app1neard.corpapp.data.database.dao.WorkoutSessionDao
import shejianguo.app1neard.corpapp.data.entity.WorkoutSessionEntity

@Database(
    entities = [WorkoutSessionEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class SNPCPAppDatabase : RoomDatabase() {

    abstract fun workoutSessionDao(): WorkoutSessionDao
}
