package org.lucashos.tmdb

import android.os.Bundle
import android.util.Log
import org.lucashos.core.BaseActivity
import org.lucashos.core.api.ApiClient
import org.lucashos.core.dummy.CoreDummy
import org.lucashos.data.dummy.DataDummy
import org.lucashos.feature.dummy.FeatureDummy
import org.lucashos.tmdb.dummy.ApplicationDummy
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    @Inject
    lateinit var coreDummy: CoreDummy

    @Inject
    lateinit var dataDummy: DataDummy

    @Inject
    lateinit var featureDummy: FeatureDummy

    @Inject
    lateinit var applicationDummy: ApplicationDummy

    @Inject
    lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("dgr", coreDummy.whereAmI())
        Log.d("dgr", dataDummy.whereAmI())
        Log.d("dgr", featureDummy.whereAmI())
        Log.d("dgr", applicationDummy.whereAmI())

    }
}