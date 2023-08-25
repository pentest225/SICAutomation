package ci.orange.sicdomotique

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ci.orange.presentation.navigation.TUYA_HOME_ROUTE
import ci.orange.presentation.navigation.tuyaNavGraph

@Composable
fun MainNavigation(
    navController:NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = TUYA_HOME_ROUTE
    ){
       tuyaNavGraph(navController)
    }

}