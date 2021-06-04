package com.example.redditpostgrabber

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import java.io.Serializable

/*
 * Classes modelo para armazenamento dos dados obtidos via JSON.
 */


data class Base (
    val data : Data
)

data class Data (
    val children : List<Children>,
)

data class Children (
    @SerializedName("data") val topic : Topic
)

data class Topic (
    val title: String,
    @SerializedName("author_fullname") val author: String,
    @SerializedName("selftext") val body: String
) : Serializable

interface RecyclerViewClickInterface {
    fun onClick(position: Int)
}

interface APIService {
    @GET("/r/androiddev.json?raw_json=1")
    fun getTopic() : Call<Base>
}