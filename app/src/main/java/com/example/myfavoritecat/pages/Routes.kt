package com.example.myfavoritecat.pages

enum class Routes(val route: String) {
    MY_CATS("MyCatsPage"),
    SEARCH_CATS("SearchCatsPage"),
    OBSERVE_SEARCH_CATS("ObserveSearchCatsPage?name={name}")
}

val INIT_ROUTE = Routes.MY_CATS.route