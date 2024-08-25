package com.only.ekacareassignment

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.only.ekacareassignment.presentation.FormScreen
import com.only.ekacareassignment.ui.theme.EkaCareAssignmentTheme
import com.only.ekacareassignment.ui.theme.TextBorder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EkaCareAssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SplashScreen(SplashScreenViewModel())
                }
            }
        }
    }
}


class SplashScreenViewModel : ViewModel() {

    val isLoading = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            delay(2000)
            isLoading.value = false
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashScreen(viewModel: SplashScreenViewModel) {

    val isLoading by viewModel.isLoading.collectAsState(initial = true)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TextBorder)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo3),
            contentDescription = "Splash Screen Image",
            modifier = Modifier.align(Alignment.Center)
        )

        if (!isLoading) {
            FormScreen(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EkaCareAssignmentTheme {
    }
}