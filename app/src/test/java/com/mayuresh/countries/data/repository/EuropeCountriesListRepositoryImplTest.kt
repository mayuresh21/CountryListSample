package com.mayuresh.countries.data.repository

import com.mayuresh.countries.TestData
import com.mayuresh.countries.data.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class EuropeCountriesListRepositoryImplTest {

    @MockK
    lateinit var apiService: ApiService

    private lateinit var countriesListRepositoryImpl: EuropeanCountriesListRepositoryImpl

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
        countriesListRepositoryImpl = EuropeanCountriesListRepositoryImpl(apiService)
    }

    @Test
    fun `Should validate api service call`() = runTest {
        // Given
        val apiResponse = Response.success(TestData.getCountriesData())

        // When
        coEvery {
            apiService.getEuropeCountriesData()
        } returns apiResponse

        countriesListRepositoryImpl.getEuropeCountriesList()
        // Then
        coVerify(atLeast = 1) { apiService.getEuropeCountriesData() }
    }
}