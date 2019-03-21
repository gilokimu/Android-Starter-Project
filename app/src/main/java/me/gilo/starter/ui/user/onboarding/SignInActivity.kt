package me.gilo.starter.ui.user.onboarding


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.content_sign_in.*
import me.gilo.starter.R
import me.gilo.starter.common.BaseActivity
import me.gilo.starter.common.Status
import me.gilo.starter.ui.home.HomeActivity
import me.gilo.starter.ui.state.ProgressDialogFragment
import me.gilo.starter.utils.AppUtils
import me.gilo.starter.viewmodels.UserViewModel
import org.json.JSONObject
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignInActivity : BaseActivity() {


    lateinit var viewModel : UserViewModel
    val TAG = this::getLocalClassName

    private lateinit var progressDialog: ProgressDialogFragment
    private val pattern = Pattern.compile(EMAIL_PATTERN)
    private var matcher: Matcher? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        viewModel = getViewModel(UserViewModel::class.java)

        title = "Sign In"

        bNext.setOnClickListener{
            login()
        }

        tvSignUp.setOnClickListener{startActivity(Intent(baseContext, SignUpActivity::class.java))}
    }

    private fun login() {
        if (validates()) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            viewModel.login(email, password).observe(this, Observer {
                response->
                when (response!!.status()){
                    Status.LOADING ->{
                        showLoading("Performing log in", "This will only take a short while")
                    }

                    Status.SUCCESS ->{
                        stopShowingLoading()
                        startActivity(Intent(baseContext, HomeActivity::class.java))

                        (AppUtils(baseContext)).saveToken(response.data().response.token, response.data().response.expiry)

                    }

                    Status.ERROR ->{
                        stopShowingLoading()

                        var message: String
                        var loginError = JSONObject(response.error().message)

                        if (loginError.has("status_message")){
                            message = loginError.getString("status_message")
                        }else{
                            message = response.error().message.toString()
                        }

                        Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                    }

                    Status.EMPTY ->{
                        stopShowingLoading()
                    }

                }
            })



        } else {
            Toast.makeText(this, "Please correct the information entered", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validates(): Boolean {
        var validation = true

        tilEmail.isErrorEnabled = false
        tilPassword.isErrorEnabled = false

        val email = tilEmail.editText!!.text.toString()

        if (email.isEmpty()) {
            tilEmail.error = "This cannot be left blank!"
            validation = false
        }

        return validation
    }

    private fun validateEmail(email: String): Boolean {
        matcher = pattern.matcher(email)
        return matcher!!.matches()
    }

    private fun showLoading(title: String, message: String) {
        val manager = supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    private fun stopShowingLoading() {
        progressDialog.dismiss()
    }

    companion object {
        private const val EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
    }

}
