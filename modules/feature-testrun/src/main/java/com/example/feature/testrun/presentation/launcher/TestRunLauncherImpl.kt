package com.example.feature.testrun.presentation.launcher

import android.app.Activity
import android.content.Intent
import com.example.feature.testrun.presentation.view.TestRunActivity
import com.example.testrun.presentation.TestRunLauncher
import javax.inject.Inject

class TestRunLauncherImpl @Inject constructor() : TestRunLauncher {
    override fun launch(activity: Activity) {
        activity.startActivity(Intent(activity, TestRunActivity::class.java))
    }
}