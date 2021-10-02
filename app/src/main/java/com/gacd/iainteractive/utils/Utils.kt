package com.gacd.iainteractive.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.gacd.iainteractive.data.network.Resource
import com.google.android.material.snackbar.Snackbar

fun<A: Activity> Activity.startNewActivity(activity: Class<A>){

    Intent(this,activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean){
    visibility = if(isVisible) View.VISIBLE else View.GONE
}
//Show snackBar
fun View.snackBar(
    message: String,
    action: (() -> Unit)? = null){
    val snackbar = Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

//When the user does not have internet connection
fun Fragment.handleApiErrors(
    failure : Resource.Failure,
    retry : (() -> Unit)? = null
){
    when{
        failure.isNetworkError -> requireView().snackBar("Please check your internet connection", retry)
    }
}