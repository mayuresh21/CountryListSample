package com.mayuresh.countries.presentation.ui.components

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mayuresh.countries.presentation.ui.theme.EuropeCountryTypography

/**
 * This is common toolbar
 * @param title // title name
 * @param onUpClick // on back button back press
 * @param modifier
 * @param isShowBackButton // this variable is for back button visibility
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String,
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    isShowBackButton: Boolean,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = EuropeCountryTypography.titleMedium,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier.statusBarsPadding(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        navigationIcon = {
            if (isShowBackButton) {
                IconButton(onClick = onUpClick) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}
