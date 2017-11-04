package com.agoda.sneakershop.screen.sneaker.list.mapper

import com.agoda.sneakershop.data.entity.SneakerEntity
import com.agoda.sneakershop.extension.shouldEqual
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * OmiseGO
 *
 *
 * Created by Phuchit Sirimongkolsathien on 11/4/2017 AD.
 * Copyright © 2017 OmiseGO. All rights reserved.
 */

@RunWith(MockitoJUnitRunner::class)
class SneakerListItemViewModelMapperImplTest {

    lateinit var mapper: SneakerListItemViewModelMapperImpl

    @Before
    fun setUp() {
        mapper = SneakerListItemViewModelMapperImpl()
    }

    @Test
    fun map() {
        // Arrange
        val entity = SneakerEntity(1L, "Adidas Neo Boost V2", 3, "Sneaker", 3, "Neo", 300.0, "https://google.com/image.png")

        // Action
        val viewModel = mapper.map(entity)

        // Assert
        with(viewModel){
            categoryName shouldEqual "Sneaker"
            collectionName shouldEqual "Neo"
            id shouldEqual 1L
            name shouldEqual "Adidas Neo Boost V2"
        }

    }
}