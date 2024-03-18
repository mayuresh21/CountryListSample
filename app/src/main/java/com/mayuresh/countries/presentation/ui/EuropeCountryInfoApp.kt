package com.mayuresh.countries.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.mayuresh.countries.R
import com.mayuresh.countries.presentation.ui.components.AppToolbar
import com.mayuresh.countries.presentation.ui.theme.NYTimesTheme

@Composable
fun EuropeanCountriesApp() {
    NYTimesTheme {
        val navController = rememberNavController()
        var isShowBackButton by remember { mutableStateOf(false) }
        val defaultTitle = stringResource(id = R.string.title)
        var actionBarTitle by remember { mutableStateOf(defaultTitle) }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.route) {
                CountryDestinations.COUNTRY_LIST_ROUTE -> {
                    isShowBackButton = false
                    actionBarTitle = defaultTitle
                }

                else -> {
                    isShowBackButton = true
                }
            }
        }
        Scaffold(
            topBar = {
                AppToolbar(
                    title = actionBarTitle,
                    onUpClick = {
                        navController.navigateUp()
                    },
                    isShowBackButton = isShowBackButton
                )

            },
            content = { paddingValues ->
                NYTimesNavGraph(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    actionBarTitle = {
                        actionBarTitle = it ?: ""
                    }
                )
            })

    }
}

