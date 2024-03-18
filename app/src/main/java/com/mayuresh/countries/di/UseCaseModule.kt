package com.mayuresh.countries.di

import android.content.Context
import com.mayuresh.countries.domain.repository.CountryDetailsRepository
import com.mayuresh.countries.domain.repository.EuropeanCountriesListRepository
import com.mayuresh.countries.domain.usecase.GetCountryInformationUseCase
import com.mayuresh.countries.domain.usecase.GetEuropeanCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetEuropeanCountriesUseCase(europeanCountriesListRepository: EuropeanCountriesListRepository): GetEuropeanCountriesUseCase {
        return GetEuropeanCountriesUseCase(europeanCountriesListRepository)
    }

    @Provides
    fun provideGetCountryInformationUseCase(countryDetailsRepository: CountryDetailsRepository, @ApplicationContext context: Context): GetCountryInformationUseCase {
        return GetCountryInformationUseCase(countryDetailsRepository = countryDetailsRepository, context = context )
    }

}