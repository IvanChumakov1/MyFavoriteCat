package com.example.myfavoritecat.pages

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myfavoritecat.ViewModels.SelectingCatViewModel

@Composable
fun AppNavigationHost(navController: NavHostController) {
    val selectedViewModel: SelectingCatViewModel = hiltViewModel()
    NavHost(navController, startDestination = INIT_ROUTE) {
        composable(Routes.MY_CATS.route) {
            MyCatsPage(onNavigationToSearchCatsPage = {
                navController.navigate(
                    Routes.SEARCH_CATS.route
                )
            })
        }
        composable(Routes.SEARCH_CATS.route) {
            SearchCatsPage(
                onNavigateBack = {
                    navController.navigate(Routes.MY_CATS.route)
                }/*,
                onNavigateToObserveSearchCatsPage = { name ->
                    navController.navigate(createObservSearchCatsRoute(name))
                },
                selectingViewModel = selectedViewModel*/
            )
        }
        composable(Routes.OBSERVE_SEARCH_CATS.route, arguments = listOf(
            navArgument("name") { defaultValue = "" }
        )) {
            ObserveSearchCatsPage(
                onNavigateBack = {
                    navController.navigate(
                        Routes.SEARCH_CATS.route,
                    )
                },
                name = it.arguments?.getString("name") ?: "",
                selectingViewModel = selectedViewModel
            )
        }
    }
}

fun createObservSearchCatsRoute(name: String): String {
    return "ObserveSearchCatsPage?name=$name"
}