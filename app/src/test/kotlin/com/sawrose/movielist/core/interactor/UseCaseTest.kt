package com.sawrose.movielist.core.interactor

import com.sawrose.movielist.AndroidTest
import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.functional.Either
import com.sawrose.movielist.core.functional.Either.Right
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Test

class UseCaseTest : AndroidTest() {

    private val TYPE_TEST = "Test"
    private val TYPE_PARAM = "ParamTest"

    private val useCase = MyUseCase()

    @Test
    fun `running use case should return 'Either' of use case Type`() {
        val params = MyParam(TYPE_PARAM)
        val result = runBlocking { useCase.run(params) }

        result shouldEqual Right(MyType(TYPE_TEST))
    }

    @Test
    fun `should return correct data when executing use case`() {
        var result: Either<Failure, MyType>? = null

        val params = MyParam("TestParam")
        val onResult = { myResult: Either<Failure, MyType> -> result = myResult }

        runBlocking { useCase(params, onResult) }
        result shouldEqual Right(MyType(TYPE_TEST))
    }

    data class MyType(val name: String)
    data class MyParam(val name: String)

    private inner class MyUseCase : UseCase<MyType, MyParam>() {
        override suspend fun run(params: MyParam): Either<Failure, MyType> = Right(
                MyType(TYPE_TEST))
    }
}