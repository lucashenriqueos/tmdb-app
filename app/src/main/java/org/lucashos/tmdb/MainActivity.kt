package org.lucashos.tmdb

import android.os.Bundle
import android.util.Log
import org.lucashos.core.base.BaseActivity
import org.lucashos.core.api.ApiClient
import org.lucashos.core.dummy.CoreDummy
import org.lucashos.data.dummy.DataDummy
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.feature.dummy.FeatureDummy
import org.lucashos.tmdb.dummy.ApplicationDummy
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}