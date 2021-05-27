package com.example.redditpostgrabber

import com.google.gson.annotations.SerializedName

data class Base (
    @SerializedName("data") val data : Data
)

data class Data (
    @SerializedName("children") val children : List<Children>,
)

data class Children (
    @SerializedName("data") val topic : Topic
)

data class Topic(
    @SerializedName("title") val title: String,
    @SerializedName("author_fullname") val author: String
)