package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentsServices {

    //https://lion-scholl.cyclic.app/v1/lion-school/
    @GET("alunos")
    fun getStudentsByCourse (@Query("curso") curso: String): Call<StudentsList>

}