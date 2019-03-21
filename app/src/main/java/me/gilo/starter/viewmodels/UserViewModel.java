package me.gilo.starter.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import me.gilo.starter.common.CallBackLiveData;
import me.gilo.starter.common.CompletionGenericLiveData;
import me.gilo.starter.common.CompletionLiveData;
import me.gilo.starter.common.DocumentLiveData;
import me.gilo.starter.data.response.ApiResponse;
import me.gilo.starter.data.response.LoginResponse;
import me.gilo.starter.models.User;
import me.gilo.starter.repo.FirebaseRepository;
import me.gilo.starter.repo.UserRepository;

import javax.inject.Inject;


public final class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final MutableLiveData<String> id = new MutableLiveData<>();
    private final FirebaseRepository firebaseRepository;

    @Inject
    UserViewModel(UserRepository userRepository, FirebaseRepository firebaseRepository) {
        this.userRepository = userRepository;
        this.firebaseRepository = firebaseRepository;
    }

    public CallBackLiveData<LoginResponse> login(String username, String password) {
        return userRepository.login(username, password);
    }

    public CallBackLiveData<ApiResponse> register(User user) {
        return userRepository.register(user);
    }

    public CompletionGenericLiveData<AuthResult> firebaseLogin(String email, String password) {
        return firebaseRepository.login(email, password);
    }

    public CompletionGenericLiveData<AuthResult> signUp(String email, String password) {
        return firebaseRepository.signUp(email, password);
    }

    public CompletionLiveData addUser(User user, OnSuccessListener successListener, OnFailureListener failureListener) {
        return firebaseRepository.addUser(user, successListener, failureListener);
    }

    public DocumentLiveData<User> user(String user_id) {
        return firebaseRepository.user(user_id);
    }

    public DocumentLiveData<User> user() {
        return firebaseRepository.user();
    }

    public CompletionLiveData updateUser(User user) {
        user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());

        return firebaseRepository.update(user);
    }

}