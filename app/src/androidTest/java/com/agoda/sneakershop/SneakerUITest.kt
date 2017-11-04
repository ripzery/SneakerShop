package com.agoda.sneakershop

import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.sneakershop.ListScreen.Item
import com.agoda.sneakershop.screen.sneaker.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 11/4/2017 AD.
 * Copyright © 2017 OmiseGO. All rights reserved.
 */

@RunWith(AndroidJUnit4::class)
class SneakerUITest {
    @JvmField
    @Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = ListScreen()
    val detail = DetailScreen()

    @Test
    fun testDataLoading() {

    }

    @Test
    fun testSearch() {
        fun testOriginalList() {
            screen {
                recycler {
                    firstChild<Item> {
                        name { hasText("Nike SF Air Force 1 Mid") }
                    }
                    lastChild<Item> {
                        name { hasText("JORDAN SUPER.FLY 2017") }
                    }
                }
            }
        }

        screen {
            // Assert
            recycler {
                firstChild<Item> {
                    name { hasText("Nike SF Air Force 1 Mid") }
                }
                lastChild<Item> {
                    name { hasText("JORDAN SUPER.FLY 2017") }
                }
            }

            // Action
            searchButton { click() }

            // Action
            searchEdit {
                typeText("ZOOM")
                act { pressImeActionButton() }
                idle()
            }

            // Assert
            recycler {
                firstChild<Item> {
                    name { hasText("NIKE AIR ZOOM ELITE 9") }
                }

                lastChild<Item> {
                    name { hasText("NIKE AIR ZOOM VOMERO 12") }
                    price { hasText("$ 100") }
                }
            }

            searchBack { click() }

//            testOriginalList()
            recycler {
                firstChild<Item> {
                    name { hasText("Nike SF Air Force 1 Mid") }
                }
                lastChild<Item> {
                    name { hasText("JORDAN SUPER.FLY 2017") }
                }
            }

            testOriginalList()
        }
    }

    @Test
    fun testDetails() {
        screen {
            recycler {
                childAt<Item>(3) { click() }
            }
        }

        detail {
            name { hasText("NIKE AIR MAX 1 ULTRA FLYKNIT") }
            category { hasText("Lifestyle") }
            pressBack()
        }

        screen {
            recycler { isDisplayed() }
        }
    }
}