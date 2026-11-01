package shejianguo.app1neard.corpapp.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import shejianguo.app1neard.corpapp.ui.composable.screen.history.SNPCPHistoryScreen
import shejianguo.app1neard.corpapp.ui.composable.screen.home.SNPCPHomeScreen
import shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.SNPCPOnboardingScreen
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.SNPCPProfileScreen
import shejianguo.app1neard.corpapp.ui.composable.screen.settings.SNPCPSettingsScreen
import shejianguo.app1neard.corpapp.ui.composable.screen.splash.SNPCPSplashScreen
import shejianguo.app1neard.corpapp.ui.composable.screen.statistics.SNPCPStatisticsScreen
import shejianguo.app1neard.corpapp.ui.composable.screen.workout.SNPCPWorkoutScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    showFilters: Boolean,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            SNPCPSplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(route = NavRoute.Onboarding) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Onboarding> {
            SNPCPOnboardingScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(NavRoute.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Home>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1neard://app/home" })
        ) {
            SNPCPHomeScreen(
                showFilters = showFilters,
                onWorkoutClick = { workoutId ->
                    navController.navigate(route = NavRoute.Workout(workoutId = workoutId))
                },
            )
        }

        composable<NavRoute.Workout>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1neard://app/workout/{workoutId}" })
        ) { backStackEntry ->
            val workout: NavRoute.Workout = backStackEntry.toRoute()
            SNPCPWorkoutScreen(
                workoutId = workout.workoutId,
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable<NavRoute.History>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1neard://app/history" })
        ) {
            SNPCPHistoryScreen()
        }

        composable<NavRoute.Statistics>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1neard://app/statistics" })
        ) {
            SNPCPStatisticsScreen()
        }

        composable<NavRoute.Profile>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1neard://app/profile" })
        ) {
            SNPCPProfileScreen(
                onSettingsClick = { navController.navigate(route = NavRoute.Settings) },
            )
        }

        composable<NavRoute.Settings>(
            deepLinks = listOf(navDeepLink { uriPattern = "app1neard://app/settings" })
        ) {
            SNPCPSettingsScreen()
        }
    }
}