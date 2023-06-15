package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ResultsServices {

    @GET("alunos/{matricula}")
    fun getAlunosByMatricula(@Path("matricula") matricula: String): Call<StudentResults>

}