package com.example.diariodeclass.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


fun LoginViewModel(){
    private val _uiState = MutableStateFlow(LoginViewModel())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(LoginUIState)

    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    var login by mutableStateOf("")
    private set
    var senha by mutableStateOf("")
    private set

    fun mudarTextoLogin(textoLogin: String){
        login = textoLogin
    }
    fun mudarTextoSenha(textoSenha: String){
        senha = textoSenha
    }

}


