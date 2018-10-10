package com.sawrose.movielist.features.model

import com.sawrose.movielist.core.extension.empty

data class Movie(val id: Int, val poster: String) {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}