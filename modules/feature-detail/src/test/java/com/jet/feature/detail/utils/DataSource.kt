package com.jet.feature.detail.utils

import com.jet.search.domain.model.Photo
import com.jet.search.presentation.model.PhotoUiModel

val photoUi = PhotoUiModel(
    localId = "test_8734",
    id = 8734,
    comments = 7,
    downloads = 5,
    largeImageURL = "test",
    previewURL = "https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg",
    likes = 2,
    tags = "#test #test #test",
    userName = "test123",
    userId = 123,
)

val photo = Photo(
    localId = "test_8734",
    id = 8734,
    comments = 7,
    downloads = 5,
    largeImageURL = "test",
    previewURL = "https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg",
    likes = 2,
    tags = "test, test, test",
    userName = "test123",
    userId = 123,
    searchTerm = "test"
)