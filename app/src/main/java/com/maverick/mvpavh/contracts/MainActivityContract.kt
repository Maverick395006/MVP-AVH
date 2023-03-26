package com.maverick.mvpavh.contracts

import com.maverick.mvpavh.network.model.UniversityDTO

interface MainActivityContract {

    interface View {
        fun onLoading()
        fun onSuccess(list: List<UniversityDTO>)
        fun onError(message: String)
    }

    interface Presenter {
        fun getUniversity(country: String)
        fun onDestroy()
    }

    interface Model {
        interface OnFinishListener {
            fun onLoading()
            fun onSuccess(list: List<UniversityDTO>)
            fun onError(message: String)
        }
        suspend fun fetchUniversity(onFinishListener: OnFinishListener,country: String)
    }

}