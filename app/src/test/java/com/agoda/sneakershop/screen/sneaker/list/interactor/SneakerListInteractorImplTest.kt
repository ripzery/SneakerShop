package com.agoda.sneakershop.screen.sneaker.list.interactor

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.screen.sneaker.list.mapper.SneakerListItemViewModelMapper
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * OmiseGO
 *
 *
 * Created by Phuchit Sirimongkolsathien on 11/4/2017 AD.
 * Copyright © 2017 OmiseGO. All rights reserved.
 */

@RunWith(MockitoJUnitRunner::class)
class SneakerListInteractorImplTest {

    lateinit var interactor: SneakerListInteractorImpl

    @Mock
    lateinit var repository: SneakerRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Mock
    lateinit var mapper: SneakerListItemViewModelMapper

    @Before
    fun setUp() {
        interactor = SneakerListInteractorImpl(repository, schedulerProvider, mapper)
    }

    @Test
    fun getSneakers() {
        // Arrange
        val query = "AirMax"
        val entity = SneakerEntity()
        val viewModel = SneakerListItemViewModel(1L, "Adidas Neo", "Sneaker", "Neo", 200.0, "http://google.com")

        whenever(repository.getSneakers(query)).thenReturn(Single.just(listOf(entity)))
        whenever(schedulerProvider.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulerProvider.main()).thenReturn(Schedulers.trampoline())
        whenever(mapper.map(entity)).thenReturn(viewModel)

        // Action
        val testObserver = interactor.getSneakers(query).test()

        // Assert
        verify(repository).getSneakers(query)
        testObserver.run {
            assertValue((listOf(viewModel)))
            assertNoErrors()
        }
    }

}