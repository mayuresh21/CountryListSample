package com.mayuresh.countries.di

import com.mayuresh.countries.data.repository.CountryDetailsRepositoryImpl
import com.mayuresh.countries.data.repository.EuropeanCountriesListRepositoryImpl
import com.mayuresh.countries.domain.repository.CountryDetailsRepository
import com.mayuresh.countries.domain.repository.EuropeanCountriesListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Provide the [EuropeanCountriesListRepository] instance
     *
     * @param europeCountriesListRepositoryImpl this [EuropeanCountriesListRepository] class is implemented in the [EuropeanCountriesListRepositoryImpl] interface
     * @return the [EuropeanCountriesListRepository]  instance
     */
    @Binds
    abstract fun bindCountryListRepository(
        europeCountriesListRepositoryImpl: EuropeanCountriesListRepositoryImpl
    ): EuropeanCountriesListRepository

    /**
     * Provide the [CountryDetailsRepository] instance
     *
     * @param countryDetailsRepositoryImpl this [CountryDetailsRepository] class is implemented in the [CountryDetailsRepositoryImpl] interface
     * @return the [CountryDetailsRepository]  instance
     */
    @Binds
    abstract fun bindCountryDetailsRepository(
        countryDetailsRepositoryImpl: CountryDetailsRepositoryImpl
    ): CountryDetailsRepository
}