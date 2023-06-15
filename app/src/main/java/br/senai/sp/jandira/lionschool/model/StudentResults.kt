package br.senai.sp.jandira.lionschool.model

data class StudentResults(
    var nome: String,
    var foto: String,
    var matricula: String,
    var status: String,
    var disciplinas: List<Matters>
)
