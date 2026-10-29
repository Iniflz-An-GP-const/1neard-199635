package shejianguo.app1neard.corpapp.di

import androidx.room.Room
import shejianguo.app1neard.corpapp.data.database.SNPCPAppDatabase
import org.koin.dsl.module

private const val DB_NAME = "snpcp_sport_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = SNPCPAppDatabase::class.java,
            name = DB_NAME,
        ).build()
    }

    single { get<SNPCPAppDatabase>().workoutSessionDao() }
}
