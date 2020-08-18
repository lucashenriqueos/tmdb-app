package org.lucashos.core.dialog

import android.app.AlertDialog
import android.content.Context
import org.lucashos.core.R

class ErrorDialog(context: Context?) : AlertDialog(context) {

    fun showDialog(title: String? = null, message: String? = null) {
        androidx.appcompat.app.AlertDialog.Builder(context)
            .setMessage(message ?: context.getString(R.string.error_message))
            .setTitle(title ?: context.getString(R.string.error_title))
            .setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}