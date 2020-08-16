package org.lucashos.feature.topmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.reactivex.Single
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.lucashos.domain.entity.MovieBO
import org.lucashos.domain.entity.TopRatedMoviesBO
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.utils.Either
import org.lucashos.feature.RxImmediateSchedulerRule

@RunWith(JUnit4::class)
class TopMoviesViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    lateinit var topMoviesUseCase: ListTopMoviesUseCase

    @InjectMockKs
    lateinit var viewModel: TopMoviesViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Should return top rated movies on Either`() {
        val mock = createTopRatedMoviesMock()
        every {
            topMoviesUseCase.execute(any())
        } returns Single.just(mock)
        viewModel.getTopMovies(1)
        val result = viewModel.topMoviesLiveData.value as Either
        assertTrue(result.isRight)
        result.shouldBe(Either.Right(mock))
    }

    @Test
    fun `Should return Exception on Either`() {
        val mock = Exception("Dummy")
        every {
            topMoviesUseCase.execute(any())
        } returns Single.error(mock)
        viewModel.getTopMovies(1)
        val result = viewModel.topMoviesLiveData.value as Either
        assertTrue(result.isLeft)
        result.shouldBe(Either.Left(mock))
    }

    private fun createTopRatedMoviesMock(): TopRatedMoviesBO {
        return TopRatedMoviesBO(
            page = 1,
            totalPages = 1,
            movies = arrayListOf(
                MovieBO(
                    title = "Mocky",
                    releaseDate = "2019-10-10",
                    genres = arrayListOf(1),
                    overview = "This is a mocky film",
                    posterPath = "/mocky.jpg",
                    rating = 8.0
                )
            )
        )
    }
}
