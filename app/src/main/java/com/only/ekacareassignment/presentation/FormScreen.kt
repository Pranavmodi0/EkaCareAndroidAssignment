package com.only.ekacareassignment.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.only.ekacareassignment.R
import com.only.ekacareassignment.data.entity.UserEntity
import com.only.ekacareassignment.ui.theme.BackgroundBottom
import com.only.ekacareassignment.ui.theme.BackgroundTop
import com.only.ekacareassignment.ui.theme.TextBorder

@Composable
fun FormScreen(modifier: Modifier) {

    val viewModel = hiltViewModel<FormViewModel>()
    Content(formViewModel = viewModel, modifier = modifier)
}

@Composable
fun Content(formViewModel: FormViewModel, modifier: Modifier) {
    val gradientShader = listOf(TextBorder, BackgroundTop)

    LaunchedEffect(key1 = true, block = {
        formViewModel.getUserDetails()
    })

    Column(
        modifier.fillMaxSize()
        .background(BackgroundBottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            contentAlignment = Alignment.TopCenter
        ) {
            TopContent(formViewModel = formViewModel, modifier = modifier, gradientShader)
        }

        Text(
            text = "Users List",
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientShader
                )
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        BottomContent(formViewModel = formViewModel, modifier = modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContent(formViewModel: FormViewModel, modifier: Modifier, gradientShader: List<Color>) {

    val name by formViewModel.userName.collectAsStateWithLifecycle()
    val age by formViewModel.userAge.collectAsStateWithLifecycle()
    val dob by formViewModel.userDob.collectAsStateWithLifecycle()
    val address by formViewModel.userAddress.collectAsStateWithLifecycle()

    val onNameSet: (value: String) -> Unit = remember {
        return@remember formViewModel::setUserName
    }

    val onAgeSet: (value: String) -> Unit = remember {
        return@remember formViewModel::setUserAge
    }

    val onDobSet: (value: String) -> Unit = remember {
        return@remember formViewModel::setUserDob
    }

    val onAddressSet: (value: String) -> Unit = remember {
        return@remember formViewModel::setUserAddress
    }

    val onSubmit: (value: UserEntity) -> Unit = remember {
        return@remember formViewModel::insertUserDetails
    }

    val isButtonEnabled = name.isNotEmpty() && age.isNotEmpty() && dob.isNotEmpty() && address.isNotEmpty()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        BackgroundTop,
                        RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
                    )
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
                            ),
                            fontWeight = FontWeight.Bold
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
                    .padding(padding)
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
                    maxLines = 1,
                    onValueChange = { onNameSet(it) },
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
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { onAgeSet(it) },
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
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = { onDobSet(it) },
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
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = { onAddressSet(it) },
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
                        .background(
                            if (isButtonEnabled) TextBorder else Color.LightGray,
                            RoundedCornerShape(size = 15.dp))
                        .fillMaxWidth(),
                    enabled = isButtonEnabled,
                    onClick = {
                        onSubmit(
                            UserEntity(
                                name = name,
                                age = age,
                                dob = dob,
                                address = address
                            )
                        )
                    }) {
                    Text(
                        text = "Submit",
                        color = Color.White,
                    )
                }
            }
        }
    )
}

@Composable
fun BottomContent(
    formViewModel: FormViewModel,
    modifier: Modifier
){
    val contents by formViewModel.usersDetailList.collectAsStateWithLifecycle()

    val modify = modifier
        .padding(15.dp)
        .fillMaxWidth()
        .height(100.dp)

    LazyColumn(
        content = {
            items(contents) {
                val item = ImmutableUser(it)
                UserCard(wrapper = item, modify = modify)
            }
        }
    )
}

@Immutable
data class ImmutableUser(val userEntity: UserEntity)

@Composable
fun UserCard(
    wrapper: ImmutableUser,
    modify: Modifier
){
    Card(
        onClick = { /*TODO*/ },
        modifier = modify
        ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = wrapper.userEntity.name,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = wrapper.userEntity.age.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = wrapper.userEntity.dob,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = wrapper.userEntity.address,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FormScreenPreview(){
    FormScreen(modifier = Modifier)
}