package com.sawrose.movielist.features.service

import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.functional.Either
import com.sawrose.movielist.core.interactor.UseCase
import com.sawrose.movielist.core.interactor.UseCase.None
import com.sawrose.movielist.features.model.Movie
import com.sawrose.movielist.features.movies.MoviesRepository
import javax.inject.Inject

class GetMovies @Inject constructor(
        private val moviesRepository: MoviesRepository
) : UseCase<List<Movie>, None>() {

    override suspend fun run(params: None): Either<Failure, List<Movie>> = moviesRepository.movies()
}