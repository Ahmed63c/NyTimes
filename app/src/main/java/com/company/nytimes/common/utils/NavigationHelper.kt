package com.company.nytimes.common.utils

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions

object NavigationHelper {
    fun navigateInclusive(
        navController: NavController,
        @IdRes sourceId: Int,
        @IdRes destinationId: Int,
    ) {
        val navOption =
            NavOptions.Builder().apply {
                setPopUpTo(sourceId, true)
                setLaunchSingleTop(true)
            }.build()
        navController.navigate(destinationId, null, navOption)
    }

    fun navigate(
        navController: NavController,
        @IdRes sourceId: Int,
        @IdRes destinationId: Int,
    ) {
        val navOption =
            NavOptions.Builder().apply {
                setPopUpTo(sourceId, false)
                setLaunchSingleTop(false)
            }.build()
        navController.navigate(destinationId, null, navOption)
    }
}