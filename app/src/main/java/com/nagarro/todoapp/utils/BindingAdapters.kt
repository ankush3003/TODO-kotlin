package com.nagarro.todoapp.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

//Q:
// Why object, can be compiled without object,
// Why jvmstatic, how to access these methods from activity
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:task_completion_text")
    fun setProgressBarVisibility(textView: TextView, isCompleted: Boolean?) {
        isCompleted?.let {
            val status = "Completed: $isCompleted"
            textView.text = status
        } ?: run { textView.visibility = View.GONE }
    }

    @JvmStatic
    @BindingAdapter("app:isVisible")
    fun showHide(view: View, isVisible: Boolean?) {
        view.visibility = if (isVisible != null && isVisible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:showShimmerView")
    fun showHide(view: ShimmerFrameLayout, isVisible: Boolean) {
        if (isVisible) {
            view.visibility = View.VISIBLE
            view.startShimmer()
        } else {
            view.stopShimmer()
            view.visibility = View.GONE
        }
    }

}