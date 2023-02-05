package com.example.rivaldosaltandroiddevtest.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rivaldosaltandroiddevtest.domain.Resource
import com.example.rivaldosaltandroiddevtest.ui.theme.RivaldoSaltAndroidDevTestTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginId = intent.getIntExtra(USER_ID_EXTRA, 0)
        setContent {
            RivaldoSaltAndroidDevTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(loginId = loginId, viewModel = viewModel)
                }
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
        const val USER_ID_EXTRA = "USER_ID_EXTRA"
    }
}

@Composable
fun MainScreen(loginId: Int, viewModel: MainViewModel = getViewModel()) {
    val scope = rememberCoroutineScope()
    val dataUser by viewModel.dataUser.collectAsState()
    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            viewModel.getUserDetailById(id = loginId)
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = "Main") })
    }) {
        when (dataUser) {
            is Resource.Success -> {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                ) {
                    with(dataUser.data?.data) {
                        ParameterDetail(
                            parameterName = "Email",
                            parameterValue = this?.email.toString(),
                            textColor = MaterialTheme.colors.onBackground
                        )
                        ParameterDetail(
                            parameterName = "First Name", parameterValue = this?.firstName
                                ?: "", textColor = MaterialTheme.colors.onBackground
                        )
                        ParameterDetail(
                            parameterName = "Last Name",
                            parameterValue = this?.lastName.toString(),
                            textColor = MaterialTheme.colors.onBackground
                        )
                    }

                }
            }

            is Resource.Error -> {
                Toast.makeText(LocalContext.current, dataUser.message, Toast.LENGTH_SHORT).show()
            }
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()

                }

            }
        }


    }

}

@Composable
fun ParameterDetail(parameterName: String, parameterValue: String, textColor: Color) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = parameterName,
            modifier = Modifier
                .weight(1.5f),
            color = textColor
        )
        Text(text = ":", modifier = Modifier.weight(0.2f), color = textColor)
        Text(
            text = parameterValue,
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(5f),
            color = textColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RivaldoSaltAndroidDevTestTheme {
//        MainScreen()
    }
}