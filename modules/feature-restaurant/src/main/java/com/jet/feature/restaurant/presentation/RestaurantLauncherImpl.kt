package com.jet.feature.restaurant.presentation

import android.app.Activity
import android.content.Intent
import com.jet.feature.restaurant.presentation.view.RestaurantActivity
import com.jet.restaurant.presentation.RestaurantLauncher
import javax.inject.Inject

class RestaurantLauncherImpl @Inject constructor() : RestaurantLauncher {
    override fun launch(activity: Activity) {
        activity.startActivity(Intent(activity, RestaurantActivity::class.java))
    }
}