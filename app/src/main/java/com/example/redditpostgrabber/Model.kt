package com.example.redditpostgrabber

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
 * Classes modelo para armazenamento dos dados obtidos via JSON.
 */


data class Base (
    @SerializedName("data") val data : Data
)

data class Data (
    @SerializedName("children") val children : List<Children>,
)

data class Children (
    @SerializedName("data") val topic : Topic
)

data class Topic (
    @SerializedName("title") val title: String,
    @SerializedName("author_fullname") val author: String,
    @SerializedName("selftext") val body: String
) : Serializable

interface RecyclerViewClickInterface {
    fun onClick(position: Int)
}