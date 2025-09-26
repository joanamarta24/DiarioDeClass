package com.example.diariodeclass.login

import android.R.attr.value
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

fun LoginScreem(loginViewModel:loginViewModel= viewModel()) {
    val loginUIState by loginViewModel.uiState.collectAsState()

@Composable
fun CampoTextoLoginSenha(
    value: String ="",
    onValueChange:(String) -> Unit,
    label: String ="",
    isError: Boolean = false
    modifier : Modifier = Modifier
)

{
    OutlinedTextField(
        value = loginViewModel.login,
        onValueChange ={loginViewModel.mudarTextoLogin(it)},
        label={
            Text(text = label)
        }
    )
}
    Column {
        CampoTextoLoginSenha(
            value: String= "",

        )
    }

}