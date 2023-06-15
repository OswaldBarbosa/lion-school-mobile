package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET

interface CourseServices {

    //https://lion-school-backend.cyclic.app/v1/lion-school/
    @GET("cursos")
    fun getCourses (): Call<CoursesList>

}