package com.test.mynotes.utils

import android.content.Context
import android.widget.Toast

fun Context.showLongToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}