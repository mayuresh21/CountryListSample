package com.mayuresh.countries.domain.usecase

import com.mayuresh.countries.TestData
import com.mayuresh.countries.data.model.CountryModel
import com.mayuresh.countries.data.mapper.EuropeCountryListMapper
import com.mayuresh.countries.domain.repository.EuropeanCountriesListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetEuropeCountryListUseCaseImplTest {
    @MockK
    private lateinit var europeCountriesListRepository: EuropeanCountriesListRepository

    private lateinit var getEuropeCountryListUseCaseImpl: GetEuropeanCountriesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getEuropeCountryListUseCaseImpl = GetEuropeanCountriesUseCase(europeCountriesListRepository)
    }

    @Test
    fun `Should validate and pass article response success test`() = runTest {
        val response: Response<List<CountryModel>> = TestData.getCountriesResponseRetrofit(false)
        val result = EuropeCountryListMapper().mapFrom(response.body() as List<CountryModel>)
        // Given
        coEvery { europeCountriesListRepository.getEuropeCountriesList() } returns response
        // When
        val responseFlow = getEuropeCountryListUseCaseImpl.invoke()
        // Then
        responseFlow.collectLatest { data ->
            when (data) {
                is com.mayuresh.countries.data.util.Response.Success -> {
                    Assert.assertEquals("Finland", result.get(0).name)
                }

                else -> {
                    Assert.assertEquals(true, false)
                }
            }
        }
    }

    @Test
    fun `Should validate and pass article response error test`() = runTest {
        val response: Response<List<CountryModel>> = TestData.getCountriesResponseRetrofit(true)
        // Given
        coEvery { europeCountriesListRepository.getEuropeCountriesList() } returns response
        // When
        val responseFlow = getEuropeCountryListUseCaseImpl.invoke()
        // Then
        // Then
        responseFlow.collectLatest { data ->
            when (data) {
                is com.mayuresh.countries.data.util.Response.Error -> {
                    Assert.assertEquals(400, data.code)
                }

                else -> {
                    Assert.assertEquals(true, false)
                }
            }
        }
    }
}