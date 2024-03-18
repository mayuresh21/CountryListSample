package com.mayuresh.countries.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mayuresh.countries.MainCoroutineRule
import com.mayuresh.countries.domain.usecase.GetEuropeanCountriesUseCase
import com.mayuresh.countries.presentation.util.NetworkHelper
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CountryListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    private lateinit var getEuropeanCountryUseCase: GetEuropeanCountriesUseCase

    @RelaxedMockK
    private lateinit var networkHelper: NetworkHelper

    private lateinit var viewModel: CountryListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, true)
        viewModel = CountryListViewModel(
            getEuropeanCountriesUseCase = getEuropeanCountryUseCase,
            networkHelper = networkHelper,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `get countries list success test`() = runTest {

    }

    @Test
    fun `get countries list error test`() = runTest {

    }

    @Test
    fun `get countries list exception test`() = runTest {

    }

}