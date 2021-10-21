package org.lucashos.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjection
import org.lucashos.core.dialog.ErrorDialog

abstract class BaseActivity : AppCompatActivity() {

    abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AndroidInjection.inject(this)
    }

    fun showError() = ErrorDialog(this).showDialog()
}