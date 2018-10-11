package com.sawrose.movielist.features.model

import android.content.Context
import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.functional.Either
import com.sawrose.movielist.core.functional.Either.Right
import com.sawrose.movielist.core.interactor.UseCase
import com.sawrose.movielist.core.interactor.UseCase.None
import com.sawrose.movielist.core.navigation.Navigator
import com.sawrose.movielist.features.model.PlayMovie.Params
import javax.inject.Inject

class PlayMovie
@Inject constructor(private val context: Context,
        private val navigator: Navigator) : UseCase<None, Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Right(None())
    }

    data class Params(val url: String)
}