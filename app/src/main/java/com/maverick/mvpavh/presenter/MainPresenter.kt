package com.maverick.mvpavh.presenter

import com.maverick.mvpavh.contracts.MainActivityContract
import com.maverick.mvpavh.network.model.UniversityDTO
import com.maverick.mvpavh.util.launchOnMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainActivityContract.View,
    private val model: MainActivityContract.Model
) : MainActivityContract.Presenter, MainActivityContract.Model.OnFinishListener {

    val scope = CoroutineScope(Dispatchers.IO)

    override fun getUniversity(country: String) {
        scope.launch {
            model.fetchUniversity(onFinishListener = this@MainPresenter, country = country)
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }


    override fun onLoading() {
        scope.launchOnMain { view.onLoading() }
    }

    override fun onSuccess(list: List<UniversityDTO>) {
        scope.launchOnMain { view.onSuccess(list) }
    }

    override fun onError(message: String) {
        scope.launchOnMain { view.onError(message) }
    }

}