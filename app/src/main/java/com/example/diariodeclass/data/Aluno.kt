package com.rafaelcosta.diariodeclass.data

import androidx.annotation.DrawableRes

data class Aluno(
    val nome: String,
    @DrawableRes val foto: Int,
    val curso: String,
)