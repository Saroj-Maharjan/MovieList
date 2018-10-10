package com.sawrose.movielist.features.movies

import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.exception.Failure.NetworkConnection
import com.sawrose.movielist.core.exception.Failure.ServerError
import com.sawrose.movielist.core.functional.Either
import com.sawrose.movielist.core.functional.Either.Left
import com.sawrose.movielist.core.functional.Either.Right
import com.sawrose.movielist.core.platform.NetworkHandler
import com.sawrose.movielist.features.model.Movie
import com.sawrose.movielist.features.model.MovieDetails
import com.sawrose.movielist.features.model.MovieDetailsEntity
import com.sawrose.movielist.features.service.MoviesService
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {

    fun movies(): Either<Failure, List<Movie>>
    fun movieDetail(movieId: Int): Either<Failure, MovieDetails>

    class Network @Inject constructor(
            private val networkHandler: NetworkHandler,
            private val service: MoviesService
    ) : MoviesRepository {
        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> request(service.movies(), { it.map { it.toMovie() } }, emptyList())
                false,
                null -> Left(NetworkConnection())
            }
        }

        override fun movieDetail(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                true -> request(service.movieDetails(movieId), { it.toMovieDetails() },
                        MovieDetailsEntity.empty())
                false,
                null -> Left(NetworkConnection())
            }
        }

    }

    fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Right(transform((response.body() ?: default)))
                false -> Left(ServerError())
            }
        } catch (exception: Throwable) {
            Left(ServerError())
        }
    }
}