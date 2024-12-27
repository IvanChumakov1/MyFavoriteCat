package com.example.myfavoritecat.pages

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myfavoritecat.ViewModels.MyCatsViewModel
import com.example.myfavoritecat.ViewModels.ObserveSearchCatsViewModel

@Composable
fun AppNavigationHost(navController: NavHostController) {
    val observeSearchCatsViewModel: ObserveSearchCatsViewModel = hiltViewModel()
    val myCatsViewModel: MyCatsViewModel = hiltViewModel()
    NavHost(navController, startDestination = INIT_ROUTE) {
        composable(Routes.MY_CATS.route) {
            MyCatsPage(
                onNavigationToSearchCatsPage = {
                    navController.navigate(
                        Routes.SEARCH_CATS.route
                    )
                },
                onNavigationToEditCatPage = { catId ->
                    navController.navigate(createEditCatRoute(catId))
                },
                onNavigationToObserveMyCatPage = { catId ->
                    navController.navigate(createObserveMyCatRoute(catId))
                }
            )
        }
        composable(Routes.SEARCH_CATS.route) {
            SearchCatsPage(
                onNavigateBack = {
                    navController.navigate(Routes.MY_CATS.route)
                },
                onNavigateToObserveCatPage = { cat ->
                    val catId = observeSearchCatsViewModel.addCat(cat)
                    navController.navigate(createObserveCatRoute(catId))
                },
                observeSearchCatsViewModel = observeSearchCatsViewModel
            )
        }
        composable(
            route = Routes.OBSERVE_CAT.route,
            arguments = listOf(navArgument("catId") { defaultValue = "" })
        ) { backStackEntry ->
            val catId = backStackEntry.arguments?.getString("catId")
            val cat = observeSearchCatsViewModel.getCatById(catId)
            if (cat != null) {
                ObserveCatPage(
                    cat = cat,
                    onNavigateBack = {
                        navController.navigate(Routes.SEARCH_CATS.route)
                    },
                    onAddCat = {
                        navController.navigate(Routes.MY_CATS.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
        }
        composable(
            route = Routes.EDIT_CAT.route,
            arguments = listOf(navArgument("catId") { defaultValue = "" })
        ) { backStackEntry ->
            val catId = backStackEntry.arguments?.getString("catId")
            EditCatPage(
                catId = catId,
                onNavigateBack = {
                    navController.navigate(Routes.MY_CATS.route) {
                        popUpTo(0)
                    }
                },
                myCatsViewModel = myCatsViewModel
            )
        }
        composable(
            route = Routes.OBSERVE_MY_CAT.route,
            arguments = listOf(navArgument("catId") { defaultValue = "" })
        ) { backStackEntry ->
            val catId = backStackEntry.arguments?.getString("catId")
            val cat = myCatsViewModel.getCatById(catId)
            if (cat != null) {
                ObserveMyCatPage(
                    cat = cat,
                    onNavigateBack = {
                        navController.navigate(Routes.MY_CATS.route)
                    }
                )
            }
        }
    }
}

fun createObserveCatRoute(catId: String): String {
    return "ObserveCatPage/$catId"
}

fun createEditCatRoute(catId: String?): String {
    return "EditCatPage/$catId"
}

fun createObserveMyCatRoute(catId: String?): String {
    return "ObserveMyCatPage/$catId"
}