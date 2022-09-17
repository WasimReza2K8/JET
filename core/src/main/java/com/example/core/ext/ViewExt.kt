package com.example.core.ext

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun RecyclerView.doNotLeak(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(RecyclerviewEventObserver(this))
}

internal class RecyclerviewEventObserver(private val recyclerView: RecyclerView) :
    LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            recyclerView.adapter = null
        }
    }
}

fun RecyclerView.asVerticalLayout(reverse: Boolean = false) {
    this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, reverse)
}

fun View.showNetworkError() {
    Snackbar.make(this, R.string.network_error, BaseTransientBottomBar.LENGTH_LONG).show()
}

fun View.showUnknownError() {
    Snackbar.make(this, R.string.unknown_error, BaseTransientBottomBar.LENGTH_LONG).show()
}
