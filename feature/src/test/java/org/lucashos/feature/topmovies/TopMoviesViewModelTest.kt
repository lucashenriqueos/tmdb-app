package org.lucashos.feature.topmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.lucashos.domain.usecase.ListTopMoviesUseCase
import org.lucashos.domain.usecase.SearchMoviesUseCase
import org.lucashos.domain.utils.Either
import org.lucashos.feature.RxImmediateSchedulerRule
import org.lucashos.feature.model.createMoviesListMock
import org.lucashos.feature.model.getDummyException

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

    @MockK
    lateinit var searchMoviesUseCase: SearchMoviesUseCase

    @InjectMockKs
    lateinit var viewModel: TopMoviesViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Should return top rated movies`() {
        val mock = createMoviesListMock()
        every {
            topMoviesUseCase.execute(any())
        } returns Single.just(mock)
        viewModel.getTopMovies(1)
        val result = viewModel.moviesListLiveData.value as Either
        assertTrue(result.isRight)
        result.shouldBe(Either.Right(mock))
    }

    @Test
    fun `Should return Exception on Either on topMovies`() {
        val mock = getDummyException()
        every {
            topMoviesUseCase.execute(any())
        } returns Single.error(mock)
        viewModel.getTopMovies(1)
        val result = viewModel.moviesListLiveData.value as Either
        assertTrue(result.isLeft)
        result.shouldBe(Either.Left(mock))
    }
}
