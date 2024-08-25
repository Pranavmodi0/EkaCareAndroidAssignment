package com.only.ekacareassignment.presentation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.only.ekacareassignment.R
import com.only.ekacareassignment.data.entity.UserEntity
import com.only.ekacareassignment.ui.theme.BackgroundBottom
import com.only.ekacareassignment.ui.theme.BackgroundTop
import com.only.ekacareassignment.ui.theme.TextBorder
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen(modifier: Modifier) {

    val context = LocalContext.current

    val view = LocalView.current
    val window = (view.context as Activity).window
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true

    DisposableEffect(context) {
        // Lock the screen orientation.
        context.requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        onDispose {
            // Release the the screen orientation lock.
            context.requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    val viewModel = hiltViewModel<FormViewModel>()
    Content(formViewModel = viewModel, modifier = modifier)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(formViewModel: FormViewModel, modifier: Modifier) {
    val gradientShader = listOf(TextBorder, TextBorder)

    LaunchedEffect(key1 = true, block = {
        formViewModel.getUserDetails()
    })

    Column(
        modifier
            .fillMaxSize()
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

        Spacer(Modifier.size(5.dp))

        BottomContent(formViewModel = formViewModel, modifier = modifier)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContent(formViewModel: FormViewModel, modifier: Modifier, gradientShader: List<Color>) {

    val submissionStatus by formViewModel.submissionStatus.collectAsState()

    val context = LocalContext.current

    submissionStatus?.let { isSuccess ->
        LaunchedEffect(isSuccess) {
            if (isSuccess) {
                Toast.makeText(context, "Submission Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Submission Failed", Toast.LENGTH_SHORT).show()
            }
            formViewModel.submissionStatus
        }
    }

    val dateState = rememberDatePickerState()
    val millisToLocalDate = dateState.selectedDateMillis?.let {
        CustomDatePicker().convertMillisToLocalDate(it)
    }
    val dateToString = millisToLocalDate?.let {
        CustomDatePicker().dateToString(millisToLocalDate)
    } ?: "${LocalDate.now()}"

    val name by formViewModel.userName.collectAsStateWithLifecycle()
    val age by formViewModel.userAge.collectAsStateWithLifecycle()
    val dob by formViewModel.userDob.collectAsStateWithLifecycle()
    val address by formViewModel.userAddress.collectAsStateWithLifecycle()

    var datePickerShowing by remember {
        mutableStateOf(false)
    }

    val onNameSet: (value: String) -> Unit = remember {
        return@remember formViewModel::setUserName
    }

    val onAgeSet: (value: String) -> Unit = remember {
        return@remember formViewModel::setUserAge
    }

    val onDobSet: (value: LocalDate) -> Unit = remember {
        return@remember formViewModel::setUserDob
    }

    val onAddressSet: (value: String) -> Unit = remember {
        return@remember formViewModel::setUserAddress
    }

    val onSubmit: (value: UserEntity) -> Unit = remember {
        return@remember formViewModel::insertUserDetails
    }

    var isButtonEnabled = name.isNotEmpty() && age.isNotEmpty() && dob.isNotEmpty() && address.isNotEmpty()

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
                            )
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = BackgroundTop
                    )
                )

                Spacer(Modifier.size(5.dp))

                Image(
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.logo),
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
                                .padding(horizontal = 17.dp),
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
                            val view = LocalView.current
                            try {
                                val ageInt = age.toInt()
                                if (ageInt > 100) {
                                    Toast.makeText(view.context, "Age cannot be greater than 100", Toast.LENGTH_SHORT).show()
                                    isButtonEnabled = false
                                }
                            } catch (e: NumberFormatException) {
                            }
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

                TextButton(
                    onClick = {
                        datePickerShowing = true
                    },
                ) {
                    Box(
                        Modifier
                            .background(Color.White, RoundedCornerShape(size = 15.dp))
                            .border(
                                width = 1.dp,
                                color = TextBorder,
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                            .fillMaxWidth()
                            .height(45.dp)
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (datePickerShowing) {
                            DatePickerDialog(
                                colors = DatePickerDefaults.colors(TextBorder),
                                onDismissRequest = { datePickerShowing = false },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            datePickerShowing = false
                                            millisToLocalDate?.let { onDobSet(it) }
                                        }
                                    ) {
                                        Text(text = "OK")
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = { datePickerShowing = false }
                                    ) {
                                        Text(text = "Cancel")
                                    }
                                }
                            ) {
                                DatePicker(state = dateState, showModeToggle = true)
                            }
                        }
                        if (dob.isEmpty()) {
                            Text(
                                "Date Of Birth",
                                color = Color.Gray,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else {
                            Text(
                                text = dateToString,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

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
                            RoundedCornerShape(size = 15.dp)
                        )
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
            modifier = Modifier
                .fillMaxSize()
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
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = wrapper.userEntity.age.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = wrapper.userEntity.dob,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = wrapper.userEntity.address,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

fun Context.requireActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No activity was present but it is required.")
}