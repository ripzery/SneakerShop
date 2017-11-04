package com.agoda.sneakershop.screen.sneaker.list.mapper

import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel

/**
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 11/4/2017 AD.
 * Copyright © 2017 OmiseGO. All rights reserved.
 */

class SneakerListItemViewModelMapperImpl : SneakerListItemViewModelMapper {
    override fun map(sneaker: SneakerEntity): SneakerListItemViewModel {
        return SneakerListItemViewModel(
                id = sneaker.id,
                name = sneaker.name,
                categoryName = sneaker.categoryName,
                collectionName = sneaker.collectionName,
                price = sneaker.price,
                imageUrl = sneaker.imageUrl
        )
    }
}
 