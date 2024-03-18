package com.mayuresh.countries.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mayuresh.countries.presentation.ui.components.CountryDetailsScreenComponent
import com.mayuresh.countries.presentation.ui.components.EuropeCountryListScreenComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun NYTimesNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CountryDestinations.COUNTRY_LIST_ROUTE,
    actionBarTitle: (String?) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = CountryDestinations.COUNTRY_LIST_ROUTE,
        ) {
            EuropeCountryListScreenComponent(onCountrySelection = {
                navController.navigate(
                    route = CountryDestinations.COUNTRY_DETAIL_ROUTE.plus("/")
                        .plus(it?.countryCode ?: "")
                )
                actionBarTitle.invoke(it?.name)
            })
        }
        composable(route = CountryDestinations.COUNTRY_DETAIL_ROUTE.plus("/{code}")) { backStackEntry ->
            CountryDetailsScreenComponent(
                countryCode = backStackEntry.arguments?.getString("code") ?: ""
            )
        }
    }
}
