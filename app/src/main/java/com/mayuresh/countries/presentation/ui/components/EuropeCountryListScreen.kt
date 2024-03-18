package com.mayuresh.countries.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mayuresh.countries.R
import com.mayuresh.countries.domain.model.CountryListUiState
import com.mayuresh.countries.presentation.ui.theme.EuropeCountryTypography
import com.mayuresh.countries.presentation.ui.theme.NYTimesShapes
import com.mayuresh.countries.presentation.viewmodel.CountryListViewModel

/**
 * This composable is responsible for list view representation of articles
 * @param viewModel
 * @param onCountrySelection
 */
@Composable
fun EuropeCountryListScreenComponent(
    modifier: Modifier = Modifier,
    viewModel: CountryListViewModel = hiltViewModel(),
    onCountrySelection: (CountryListUiState?) -> Unit
) {
    val countries by viewModel.countriesUiState.collectAsState()
    val isInternetAvailable by viewModel.isInternetAvailable.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ProgressBarComposable(modifier = Modifier.fillMaxWidth(), showLoading = isLoading)
        if (countries.isNotEmpty()) {
            LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
                items(countries) { country ->
                    CountryListItem(country) {
                        onCountrySelection.invoke(it)
                    }
                }
            }
        } else if (!isInternetAvailable || isError) {
            Text(
                text = if (!isError) stringResource(id = R.string.no_articles_error_message) else stringResource(
                    id = R.string.no_articles_api_error_message
                ),
                style = EuropeCountryTypography.labelSmall,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * This composable is used for article list item view
 * @param country,
 * @param selectedCountry
 */
@Composable
fun CountryListItem(
    country: CountryListUiState, selectedCountry: (CountryListUiState) -> Unit
) {
    Card(
        shape = NYTimesShapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectedCountry.invoke(country)
            }
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val placeholderImage = R.drawable.thumbnail
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(country.flagImageUrl)
                    .placeholder(placeholderImage)
                    .build(),
                error = painterResource(placeholderImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .height(50.dp)
                    .width(50.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = country.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = EuropeCountryTypography.labelMedium,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = country.region.plus("( "+country.subregion+" )"),
                    maxLines = 1,
                    style = EuropeCountryTypography.labelMedium
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

