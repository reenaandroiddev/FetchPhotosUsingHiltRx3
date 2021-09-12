package com.learn.fetchphotos.presentation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.fetchphotos.domain.entities.AlbumPhotos
import com.learn.fetchphotos.domain.usecases.GetAlbumPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AlbumPhotosViewModel @Inject constructor(private var getAlbumPhotosUseCase: GetAlbumPhotosUseCase) :
    ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val albumPhotosList = MutableLiveData<List<AlbumPhotos>>()
    val error = MutableLiveData<String>()

    fun getAlbumPhotos(albumId: Int) {
        compositeDisposable.add(
            getAlbumPhotosUseCase.getAlbumPhotos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { albumPhotosList.value = it },
                    {
                        var errorMessage: String = ""
                        if (it is HttpException) {
                            errorMessage = when (it.code()) {
                                404 -> "Server not found"
                                400 -> "400"
                                else -> "Unknown Error"
                            }
                        } else{
                            errorMessage = it.message.toString()
                        }
                        error.value = errorMessage
                    },
                    { print("Completed API Call") })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}