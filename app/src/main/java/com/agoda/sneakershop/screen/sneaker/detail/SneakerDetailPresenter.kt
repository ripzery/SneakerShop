package com.agoda.sneakershop.screen.sneaker.detail

import com.agoda.sneakershop.common.extension.plusAssign
import com.agoda.sneakershop.screen.base.BasePresenter
import com.agoda.sneakershop.screen.sneaker.detail.interactor.SneakerDetailInteractor
import com.agoda.sneakershop.screen.sneaker.detail.viewmodel.SneakerDetailViewModel

class SneakerDetailPresenter(private val interactor: SneakerDetailInteractor) : BasePresenter<SneakerDetailViewModel, SneakerDetailContract.View>(), SneakerDetailContract.Presenter {

    override fun defaultViewModel(): SneakerDetailViewModel = SneakerDetailViewModel(null, false, null)

    override fun fetchSneakerDetail(id: Long) {
        viewModel = viewModel.copy(loading = true, error = null)
        disposables += interactor.getSneakerDetail(id).subscribe(
                { content ->
                    viewModel = viewModel.copy(loading = false, content = content)
                },
                { error ->
                    viewModel = viewModel.copy(loading = false, error = error)
                })
//Use RxJava power
//        disposables += interactor.getSneakerDetail(id)
//                .map { viewModel.copy(loading = false, content = it) }
//                .onErrorReturn { viewModel.copy(loading = false, error = it) }
//                .startWith(viewModel.copy(loading = true, error = null))
//                .subscribe { viewModel = it }
    }

}