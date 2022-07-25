package com.example.portfoliogithub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String,
    val name: String,

    @SerializedName("html_url")
    val htmlURL: String,

    @SerializedName("avatar_url")
    val avatarURL: String
) : Parcelable