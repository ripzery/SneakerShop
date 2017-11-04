package com.agoda.sneakershop.screen.sneaker.list.interactor

import com.agoda.sneakershop.common.scheduler.SchedulerProvider
import com.agoda.sneakershop.data.repository.SneakerRepository
import com.agoda.sneakershop.screen.sneaker.list.mapper.SneakerListItemViewModelMapper
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import io.reactivex.Observable

/**
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 11/4/2017 AD.
 * Copyright © 2017 OmiseGO. All rights reserved.
 */

class SneakerListInteractorImpl(
        private val repository: SneakerRepository,
        private val schedulerProvider: SchedulerProvider,
        private val mapper: SneakerListItemViewModelMapper
) : SneakerListInteractor {
    override fun getSneakers(query: String): Observable<List<SneakerListItemViewModel>> {
        return repository.getSneakers(query)
                .map { it.map(mapper::map) }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main()).toObservable()
    }
}