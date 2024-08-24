package com.only.ekacareassignment.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.only.ekacareassignment.R
import com.only.ekacareassignment.ui.theme.BackgroundBottom
import com.only.ekacareassignment.ui.theme.BackgroundTop
import com.only.ekacareassignment.ui.theme.TextBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(modifier: Modifier) {

    val gradientShader = listOf(TextBorder, BackgroundTop)

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundTop, RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp))
            ) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Form",
                            color = TextBorder,
                            fontSize = 25.sp,
                            style = TextStyle(
                                brush = Brush.linearGradient(
                                    colors = gradientShader
                                )
                            )
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = BackgroundTop
                    )
                )

                Spacer(Modifier.size(16.dp))

                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "logo"
                )

                Spacer(Modifier.size(16.dp))
            }
        },
        content = { padding ->

            Column(
                modifier
                    .fillMaxSize()
                    .background(BackgroundBottom),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(45.dp),
                    value = name,
                    onValueChange = { name = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .background(Color.White, RoundedCornerShape(size = 15.dp))
                                .border(
                                    width = 1.dp,
                                    color = TextBorder,
                                    shape = RoundedCornerShape(size = 15.dp)
                                )
                                .padding(horizontal = 15.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (name.isEmpty()) {
                                Text(
                                    "Name",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    },
                )

                Spacer(Modifier.size(16.dp))

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(45.dp),
                    value = age,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { age = it },
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .background(Color.White, RoundedCornerShape(size = 15.dp))
                                .border(
                                    width = 1.dp,
                                    color = TextBorder,
                                    shape = RoundedCornerShape(size = 15.dp)
                                )
                                .padding(horizontal = 15.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (age.isEmpty()) {
                                Text(
                                    "Age",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    },
                )

                Spacer(Modifier.size(16.dp))

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(45.dp),
                    value = dob,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { dob = it },
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .background(Color.White, RoundedCornerShape(size = 15.dp))
                                .border(
                                    width = 1.dp,
                                    color = TextBorder,
                                    shape = RoundedCornerShape(size = 15.dp)
                                )
                                .padding(horizontal = 15.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (dob.isEmpty()) {
                                Text(
                                    "Date Of Birth",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    },
                )

                Spacer(Modifier.size(16.dp))

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(45.dp),
                    value = address,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = { address = it },
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .background(Color.White, RoundedCornerShape(size = 15.dp))
                                .border(
                                    width = 1.dp,
                                    color = TextBorder,
                                    shape = RoundedCornerShape(size = 15.dp)
                                )
                                .padding(horizontal = 15.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (address.isEmpty()) {
                                Text(
                                    "Address",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    },
                )

                Spacer(Modifier.size(25.dp))

                TextButton(
                    modifier = modifier
                        .padding(horizontal = 30.dp)
                        .background(TextBorder, RoundedCornerShape(size = 15.dp))
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }) {
                    Text(
                        text = "Submit",
                        color = Color.White,
                    )
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun InfoScreenPreview(){
    InfoScreen(modifier = Modifier)
}