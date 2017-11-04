package com.agoda.sneakershop.screen.sneaker.list

import com.agoda.sneakershop.common.extension.plusAssign
import com.agoda.sneakershop.screen.base.BasePresenter
import com.agoda.sneakershop.screen.sneaker.list.interactor.SneakerListInteractor
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListViewModel


/**
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 11/4/2017 AD.
 * Copyright © 2017 OmiseGO. All rights reserved.
 */

class SneakerListPresenter(
        private val interactor: SneakerListInteractor
) : BasePresenter<SneakerListViewModel, SneakerListContract.View>(), SneakerListContract.Presenter {

    override fun onSneakerClick(position: Int) {
        val id = viewModel.items[position].id
        view?.openSneakerDetail(id)
    }

    override fun defaultViewModel(): SneakerListViewModel {
        return SneakerListViewModel(
                items = listOf(),
                loading = false,
                error = null,
                query = "")
    }

    override fun fetchSneakers(query: String) {
//        val disposable = interactor.getSneakers(query)
//                .subscribe(
//                        { success ->
//                            viewModel = viewModel.copy(items = success, loading = false)
//                        },
//                        { error ->
//                            viewModel = viewModel.copy(items = listOf(), loading = false, error = error)
//                        }
//                )

        disposables += interactor.getSneakers(query)
                .map {
                    viewModel.copy(
                            loading = false,
                            items = it,
                            error = null
                    )
                }
                .onErrorReturn {
                    viewModel.copy(
                            loading = false,
                            items = listOf(),
                            error = it
                    )
                }
                .startWith(viewModel.copy(
                        loading = true,
                        query = query
                ))
                .subscribe {
                    viewModel = it
                }
    }

}