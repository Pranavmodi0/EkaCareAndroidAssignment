package com.only.ekacareassignment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.only.ekacareassignment.data.entity.UserEntity
import com.only.ekacareassignment.data.repository.Repository
import com.only.ekacareassignment.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel(){

    private val _usersDetailList = MutableStateFlow(emptyList<UserEntity>())
    val usersDetailList = _usersDetailList.asStateFlow()

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
            userUseCase(userEntity)
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
    fun setUserDob(dob:String){
        _userDob.tryEmit(dob)
    }

    private val _userAddress = MutableStateFlow("")
    val userAddress = _userAddress.asStateFlow()
    fun setUserAddress(address:String){
        _userAddress.tryEmit(address)
    }
}