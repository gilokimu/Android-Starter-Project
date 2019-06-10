package me.gilo.starter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import me.gilo.starter.common.CallBackLiveData
import me.gilo.starter.common.CompletionGenericLiveData
import me.gilo.starter.common.CompletionLiveData
import me.gilo.starter.common.DocumentLiveData
import me.gilo.starter.data.response.ApiResponse
import me.gilo.starter.data.response.LoginResponse
import me.gilo.starter.models.User
import me.gilo.starter.repo.FirebaseRepository
import me.gilo.starter.repo.UserRepository

import javax.inject.Inject


class UserViewModel @Inject
internal constructor(private val userRepository: UserRepository, private val firebaseRepository: FirebaseRepository) :
    ViewModel() {
    private val id = MutableLiveData<String>()

    fun login(username: String, password: String): CallBackLiveData<LoginResponse> {
        return userRepository.login(username, password)
    }

    fun register(user: User): CallBackLiveData<ApiResponse> {
        return userRepository.register(user)
    }

    fun firebaseLogin(email: String, password: String): CompletionGenericLiveData<AuthResult> {
        return firebaseRepository.login(email, password)
    }

    fun signUp(email: String, password: String): CompletionGenericLiveData<AuthResult> {
        return firebaseRepository.signUp(email, password)
    }

    fun addUser(
        user: User,
        successListener: OnSuccessListener<*>,
        failureListener: OnFailureListener
    ): CompletionLiveData {
        return firebaseRepository.addUser(user, successListener, failureListener)
    }

    fun user(user_id: String): DocumentLiveData<User>? {
        return firebaseRepository.user(user_id)
    }

    fun user(): DocumentLiveData<User> {
        return firebaseRepository.user()
    }

    fun updateUser(user: User): CompletionLiveData {
        user.id = FirebaseAuth.getInstance().currentUser!!.uid

        return firebaseRepository.update(user)
    }

}