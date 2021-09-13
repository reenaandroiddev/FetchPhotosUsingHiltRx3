package com.learn.fetchphotos.presentation.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learn.fetchphotos.domain.entities.AlbumPhotos
import com.learn.fetchphotos.domain.usecases.GetAlbumPhotosUseCaseImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AlbumPhotosViewModelTest {

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getAlbumPhotosUseCase: GetAlbumPhotosUseCaseImpl = mock()

    private lateinit var viewModel: AlbumPhotosViewModel

    @Before
    fun setUp() {
        viewModel = AlbumPhotosViewModel(getAlbumPhotosUseCase)
    }

    @Test
    fun `call getPhotos api and return success result`() {
        // Arrange
        whenever(getAlbumPhotosUseCase.getAlbumPhotos()).thenReturn(Observable.just(getMockData()))

        //Act
        viewModel.getAlbumPhotos(1)

        //Assert
        viewModel.albumPhotosList.observeForever{ }
        val albumList = viewModel.albumPhotosList.getOrAwaitValue ()
        val albumId =albumList[0].albumId
        assertEquals(1, albumId)


    }
}

fun getMockData() = listOf(
    AlbumPhotos(
        1,
        1,
        "accusamus beatae ad facilis cum similique qui sunt",
        "https://via.placeholder.com/600/92c952",
        "https://via.placeholder.com/150/92c952"
    )
)

class RxImmediateSchedulerRule : TestRule {

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}


