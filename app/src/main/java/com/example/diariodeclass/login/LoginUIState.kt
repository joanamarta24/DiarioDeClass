package com.example.diariodeclass.login

data class LoginUIState(
    val login: String = "",
    val senha: String ="",
    val errouLoginouSenha: Boolean = false,
    val labelLogin: String ="Login",
    val labelSenha:String ="Senha",

    )