package br.senai.sp.jandira.lionschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitFactory {

    private val BASE_URL = "https://elegant-moth-overshirt.cyclic.app/v1/lion-school/"

    private val BASE_URL2 = "https://api-lion-school-2023.cyclic.app/v1/lion-school/"

    private var retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitFactory2 = Retrofit
        .Builder()
        .baseUrl(BASE_URL2)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCourseService(): CourseServices {
        return retrofitFactory.create(CourseServices::class.java)
    }

    fun getStudentsService(): StudentsServices {
        return  retrofitFactory.create(StudentsServices::class.java)
    }

    fun getResultsAluno(): ResultsServices{
        return  retrofitFactory2.create(ResultsServices::class.java)
    }

}