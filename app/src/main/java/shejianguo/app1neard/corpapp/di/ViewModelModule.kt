package shejianguo.app1neard.corpapp.di

import shejianguo.app1neard.corpapp.ui.viewmodel.HistoryViewModel
import shejianguo.app1neard.corpapp.ui.viewmodel.HomeViewModel
import shejianguo.app1neard.corpapp.ui.viewmodel.OnboardingViewModel
import shejianguo.app1neard.corpapp.ui.viewmodel.ProfileViewModel
import shejianguo.app1neard.corpapp.ui.viewmodel.SplashViewModel
import shejianguo.app1neard.corpapp.ui.viewmodel.StatisticsViewModel
import shejianguo.app1neard.corpapp.ui.viewmodel.WorkoutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        HomeViewModel(
            workoutRepository = get(),
            profileRepository = get(),
            workoutSessionRepository = get(),
            tipRepository = get()
        )
    }

    viewModel { (workoutId: Int) ->
        WorkoutViewModel(
            workoutId = workoutId,
            workoutRepository = get(),
            workoutSessionRepository = get()
        )
    }

    viewModel {
        HistoryViewModel(
            workoutSessionRepository = get()
        )
    }

    viewModel {
        StatisticsViewModel(
            workoutSessionRepository = get()
        )
    }

    viewModel {
        ProfileViewModel(
            profileRepository = get(),
            workoutSessionRepository = get()
        )
    }
}