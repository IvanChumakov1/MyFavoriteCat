package com.example.myfavoritecat.pages

enum class Routes(val route: String) {
    MY_CATS("MyCatsPage"),
    SEARCH_CATS("SearchCatsPage"),
    OBSERVE_CAT("ObserveCatPage/{catId}"),
    EDIT_CAT("EditCatPage/{catId}"),
    OBSERVE_MY_CAT("ObserveMyCatPage/{catId}") // Добавлено
}

val INIT_ROUTE = Routes.MY_CATS.route