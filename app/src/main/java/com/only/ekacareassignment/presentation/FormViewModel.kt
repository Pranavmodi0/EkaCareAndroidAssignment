package com.only.ekacareassignment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.only.ekacareassignment.data.entity.UserEntity
import com.only.ekacareassignment.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel(){

    private val _usersDetailList = MutableStateFlow(emptyList<UserEntity>())
    val usersDetailList = _usersDetailList.asStateFlow()

    private val _submissionStatus = MutableStateFlow<Boolean?>(null)
    val submissionStatus = _submissionStatus.asStateFlow()

    init {
        getUserDetails()
    }

    fun getUserDetails(){
        viewModelScope.launch(IO) {
            userUseCase().collectLatest {
                _usersDetailList.tryEmit(it)
            }
        }
    }

    fun insertUserDetails(userEntity: UserEntity){
        viewModelScope.launch(IO) {
            try {
                userUseCase(userEntity)
                _submissionStatus.emit(true)
                clearFields()
            } catch (e: Exception) {
                _submissionStatus.emit(false)
            }
        }
    }

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()
    fun setUserName(name:String){
        _userName.tryEmit(name)
    }

    private val _userAge = MutableStateFlow("")
    val userAge = _userAge.asStateFlow()
    fun setUserAge(age:String){
        _userAge.tryEmit(age)
    }

    private val _userDob = MutableStateFlow("")
    val userDob = _userDob.asStateFlow()
    fun setUserDob(dob:LocalDate){
        _userDob.tryEmit(dob.toString())
    }

    private val _userAddress = MutableStateFlow("")
    val userAddress = _userAddress.asStateFlow()
    fun setUserAddress(address:String){
        _userAddress.tryEmit(address)
    }

    private fun clearFields() {
        _userName.tryEmit("")
        _userAge.tryEmit("")
        _userDob.tryEmit("")
        _userAddress.tryEmit("")
    }
}