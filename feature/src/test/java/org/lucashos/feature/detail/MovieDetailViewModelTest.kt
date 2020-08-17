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
import org.lucashos.domain.usecase.GetSimilarMoviesUseCase
import org.lucashos.domain.usecase.UpdateFavoriteMovieUseCase
import org.lucashos.domain.utils.Either
import org.lucashos.feature.RxImmediateSchedulerRule
import org.lucashos.feature.model.createMovieDetailMock
import org.lucashos.feature.model.createMoviesListMock
import org.lucashos.feature.model.getDummyException

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

    @MockK
    lateinit var getSimilarMoviesUseCase: GetSimilarMoviesUseCase

    @MockK
    lateinit var updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase

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
    fun `Should return Exception on Either when searching for movie details`() {
        val mock = getDummyException()
        every {
            movieDetailUseCase.execute(any())
        } returns Single.error(mock)
        viewModel.getMovieDetail(1)
        val result = viewModel.movieDetailLiveData.value as Either
        assertTrue(result.isLeft)
        result.shouldBe(Either.Left(mock))
    }

    @Test
    fun `Should return Exception on Either when id is lower then 0`() {
        viewModel.getMovieDetail(-1)
        val result = viewModel.movieDetailLiveData.value as Either
        assertTrue(result.isLeft)
    }

    @Test
    fun `Should return similar titles on Either`() {
        val mock = createMoviesListMock()
        every {
            getSimilarMoviesUseCase.execute(any())
        } returns Single.just(mock)
        viewModel.getSimilarTitles(1)
        val result = viewModel.similarMoviesLiveData.value as Either
        assertTrue(result.isRight)
        result.shouldBe(Either.Right(mock))
    }

    @Test
    fun `Should return Exception when searching similar titles`() {
        val mock = getDummyException()
        every {
            getSimilarMoviesUseCase.execute(any())
        } returns Single.error(mock)
        viewModel.getSimilarTitles(1)
        val result = viewModel.similarMoviesLiveData.value as Either
        assertTrue(result.isLeft)
        result.shouldBe(Either.Left(mock))
    }

    @Test
    fun `Should return Exception on Either when id is lower then 0 searching for similar movies`() {
        viewModel.getSimilarTitles(-1)
        val result = viewModel.similarMoviesLiveData.value as Either
        assertTrue(result.isLeft)
    }

    @Test
    fun `Should add favourite when requested movie isnt`() {
        val mock = createMovieDetailMock(isFavourite = false)
        every {
            updateFavoriteMovieUseCase.execute(UpdateFavoriteMovieUseCase.Params(id = mock.id, isFav = mock.isFavourite))
        } returns Single.just(true)

        viewModel.updateFavourite(mock)
        val result = viewModel.movieFavouriteLiveData.value as Either
        assertTrue(result.isRight)
        result.shouldBe(Either.Right(true))
    }

    @Test
    fun `Should remove from favourites when requested movie is on list`() {
        val mock = createMovieDetailMock(isFavourite = true)
        every {
            updateFavoriteMovieUseCase.execute(UpdateFavoriteMovieUseCase.Params(id = mock.id, isFav = mock.isFavourite))
        } returns Single.just(false)

        viewModel.updateFavourite(mock)
        val result = viewModel.movieFavouriteLiveData.value as Either
        assertTrue(result.isRight)
        result.shouldBe(Either.Right(false))
    }

    @Test
    fun `Should return Exception on Either when any error occurs`() {
        val exception = getDummyException()
        val mock = createMovieDetailMock(isFavourite = true)
        every {
            updateFavoriteMovieUseCase.execute(UpdateFavoriteMovieUseCase.Params(id = mock.id, isFav = mock.isFavourite))
        } returns Single.error(exception)

        viewModel.updateFavourite(mock)
        val result = viewModel.movieFavouriteLiveData.value as Either
        assertTrue(result.isLeft)
        result.shouldBe(Either.Left(exception))
    }
}
