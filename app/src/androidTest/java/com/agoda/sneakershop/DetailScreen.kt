package com.agoda.sneakershop

import com.agoda.kakao.KTextView
import com.agoda.kakao.Screen


/**
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 11/4/2017 AD.
 * Copyright © 2017 OmiseGO. All rights reserved.
 */
 
class DetailScreen: Screen<DetailScreen>() {
    val name = KTextView { withId(R.id.tvSneakerDetailName) }
    val category = KTextView { withId(R.id.tvSneakerDetailCategoryName) }
}