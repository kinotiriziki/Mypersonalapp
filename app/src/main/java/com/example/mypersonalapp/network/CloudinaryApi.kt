package com.example.mypersonalapp.network

import com.example.mypersonalapp.models.CloudinaryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CloudinaryApi {
    @Multipart
    @POST("v1_1/dv0mdjemd//image/upload")
    suspend fun uploadImage(
        @Part file:MultipartBody.Part,
        @Part("upload_preset") uploadPreset : RequestBody
    ): Response<CloudinaryResponse>

}
