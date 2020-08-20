package org.lucashos.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity(
    private val layoutId: Int,
    private val injectorModule: Module?
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        injectorModule?.let {
            loadKoinModules(injectorModule)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        injectorModule?.let {
            unloadKoinModules(injectorModule)
        }
    }
}