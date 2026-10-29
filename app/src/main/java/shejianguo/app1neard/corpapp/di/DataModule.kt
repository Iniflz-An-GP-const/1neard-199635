package shejianguo.app1neard.corpapp.di

import shejianguo.app1neard.corpapp.data.repository.OnboardingRepository
import shejianguo.app1neard.corpapp.data.repository.ProfileRepository
import shejianguo.app1neard.corpapp.data.repository.TipRepository
import shejianguo.app1neard.corpapp.data.repository.WorkoutRepository
import shejianguo.app1neard.corpapp.data.repository.WorkoutSessionRepository
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        OnboardingRepository(
            onboardingDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single { WorkoutRepository() }

    single { TipRepository() }

    single {
        WorkoutSessionRepository(
            workoutSessionDao = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single {
        ProfileRepository(
            profileDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }
}