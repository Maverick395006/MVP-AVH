package com.maverick.mvpavh.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UniversityDTO(

    @SerializedName("state-province")
    @Expose
    var stateProvince: String? = null,

    @SerializedName("domains")
    @Expose
    var domains: List<String>? = null,

    @SerializedName("web_pages")
    @Expose
    var webPages: List<String>? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("alpha_two_code")
    @Expose
    var alphaTwoCode: String? = null,

    @SerializedName("country")
    @Expose
    var country: String? = null

)