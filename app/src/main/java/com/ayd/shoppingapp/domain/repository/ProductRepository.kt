package com.ayd.shoppingapp.domain.repository

import com.ayd.shoppingapp.data.local.source.LocalDataSource
import com.ayd.shoppingapp.data.remote.source.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ProductRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    val remote = remoteDataSource
    val local = localDataSource
}