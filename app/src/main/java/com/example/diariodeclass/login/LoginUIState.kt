package com.example.diariodeclass.login

data class LoginUIState(
    val errouLoginouSenha: Boolean = false,
    val labelLogin: String ="Login",
    val labelSenha:String ="Senha",
    val loginSucesso: Boolean = false

    )