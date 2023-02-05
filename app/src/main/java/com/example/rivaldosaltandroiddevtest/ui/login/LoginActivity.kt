package com.example.rivaldosaltandroiddevtest.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rivaldosaltandroiddevtest.domain.Resource
import com.example.rivaldosaltandroiddevtest.ui.main.MainActivity
import com.example.rivaldosaltandroiddevtest.ui.theme.RivaldoSaltAndroidDevTestTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RivaldoSaltAndroidDevTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(viewModel = viewModel)
                }
            }
        }
    }
}


@Composable
fun LoginScreen(viewModel: LoginViewModel = getViewModel()) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var emailTextFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var passwordTextFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
//        TopAppBar(title = { Text(text = "Login") })
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welocme to Login Page", modifier = Modifier.padding(64.dp))
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.padding(16.dp),
                value = emailTextFieldValue,
                onValueChange = { emailTextFieldValue = it },
                label = {
                    Text(
                        text = "Email"
                    )
                })
            TextField(
                modifier = Modifier.padding(16.dp),
                value = passwordTextFieldValue,
                onValueChange = { passwordTextFieldValue = it},
                visualTransformation = PasswordVisualTransformation(),
                label = {
                    Text(
                        text =
                        "Password"
                    )
                })

            Button(
                onClick = {
                    scope.launch {
                        viewModel.login(
                            email = emailTextFieldValue.text,
                            password = passwordTextFieldValue.text
                        ).collect { resource ->
                            when (resource) {
                                is Resource.Success -> {
                                    val intent = Intent(context, MainActivity::class.java)
                                    intent.putExtra(MainActivity.USER_ID_EXTRA, 4)
                                    context.startActivity(intent)
                                }
                                is Resource.Error -> {
                                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                                }
                                is Resource.Loading -> {

                                }
                            }

                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 64.dp, end = 64.dp, top = 16.dp)
            ) {
                Text(text = "Login")
            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    RivaldoSaltAndroidDevTestTheme {
        LoginScreen()
    }
}