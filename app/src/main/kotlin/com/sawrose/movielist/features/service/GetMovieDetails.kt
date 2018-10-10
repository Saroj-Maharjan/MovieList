package com.sawrose.movielist.features.service

import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.functional.Either
import com.sawrose.movielist.core.interactor.UseCase
import com.sawrose.movielist.features.model.MovieDetails
import com.sawrose.movielist.features.movies.MoviesRepository
import com.sawrose.movielist.features.service.GetMovieDetails.Params
import javax.inject.Inject

class GetMovieDetails @Inject constructor(
        private val moviesRepository: MoviesRepository
) : UseCase<MovieDetails, Params>() {

    override suspend fun run(
            params: Params): Either<Failure, MovieDetails> = moviesRepository.movieDetail(params.id)

    data class Params(val id: Int)
}