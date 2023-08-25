package ci.orange.presentation.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ci.orange.presentation.SplashScreenView
import ci.orange.presentation.home.HomeView
import ci.orange.presentation.login.LoginHomeView

const val TUYA_HOME_ROUTE = "TUYA_SIC_HOME_ROUTE"

internal object RouteList {
    const val SPLASH = "SLASH_SCREEN"
    const val LOGIN_HOME_SCREEN = "LOGIN_HOME_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
    const val REGISTER_SCREEN = "REGISTER_SCREEN"
    const val HOME_SCREEN = "HOME_SCREEN"
}

fun NavGraphBuilder.tuyaNavGraph(navController: NavController) {
    navigation(startDestination = RouteList.SPLASH, route = TUYA_HOME_ROUTE) {
        composable(RouteList.SPLASH) {
            SplashScreenView(onNavigate = {
                navController.navigate(it)
            })
        }
        composable(RouteList.LOGIN_SCREEN) {
            LoginHomeView(onNavigate = { navController.navigate(it) })
        }

        composable(RouteList.REGISTER_SCREEN) {
            LoginHomeView(onNavigate = { navController.navigate(it) })
        }

        composable(RouteList.HOME_SCREEN) {
            HomeView()
        }
    }
}