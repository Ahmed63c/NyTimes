package com.company.domain.model

import com.google.gson.annotations.SerializedName

data class WrapperResponse(
    @field:SerializedName("results")
     val results: ArrayList<Articles>? = null
)


data class Articles(
    @SerializedName("id")
    val mId: Long? = null,

    @SerializedName("byline")
     val mByline: String? = null,

    @SerializedName("published_date")
     val mPublishedDate: String? = null,

    @SerializedName("section")
     val mSection: String? = null,

    @SerializedName("source")
     val mSource: String? = null,

    @SerializedName("title")
     val mTitle: String? = null,

    @SerializedName("url")
     val articleUrl: String? = null

)


