package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET

interface CourseServices {

    //https://lion-scholl.cyclic.app/v1/lion-school/
    @GET("cursos")
    fun getCourses (): Call<CoursesList>

}