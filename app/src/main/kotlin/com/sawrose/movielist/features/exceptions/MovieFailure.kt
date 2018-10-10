package com.sawrose.movielist.features.exceptions

import com.sawrose.movielist.core.exception.Failure.FeatureFailure

class MovieFailure {
    class ListNotAvailable : FeatureFailure()
    class NonExistentMovie : FeatureFailure()
}
