package org.lucashos.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.lucashos.domain.usecase.GetMovieDetailUseCase
import org.lucashos.domain.utils.Either
import org.lucashos.feature.RxImmediateSchedulerRule
import org.lucashos.feature.model.createMovieDetailMock

@RunWith(JUnit4::class)
class MovieDetailViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    lateinit var movieDetailUseCase: GetMovieDetailUseCase

    @InjectMockKs
    lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Should return movie detail on Either`() {
        val mock = createMovieDetailMock()
        every {
            movieDetailUseCase.execute(any())
        } returns Single.just(mock)
        viewModel.getMovieDetail(1)
        val result = viewModel.movieDetailLiveData.value as Either
        assertTrue(result.isRight)
        result.shouldBe(Either.Right(mock))
    }

    @Test
    fun `Should return Exception on Either`() {
        val mock = Exception("Dummy")
        every {
            movieDetailUseCase.execute(any())
        } returns Single.error(mock)
        viewModel.getMovieDetail(1)
        val result = viewModel.movieDetailLiveData.value as Either
        assertTrue(result.isLeft)
        result.shouldBe(Either.Left(mock))
    }
}
