package me.gilo.starter.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import me.gilo.starter.common.BaseActivity
import me.gilo.starter.ui.state.ProgressDialogFragment
import me.gilo.starter.ui.user.onboarding.SignInActivity

abstract class StarterActivity<T : ViewModel> : BaseActivity() {


    abstract var viewModel : T
    private lateinit var progressDialog: ProgressDialogFragment

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))

    }

    override fun onResume() {
        super.onResume()

//        if(FirebaseAuth.getInstance().currentUser == null){
//            startActivity(Intent(baseContext, SignInActivity::class.java))
//            finish()
//        }

    }

    fun showLoading() {
        showLoading("Please wait", "This will only take a second")
    }

    fun showLoading(title: String, message: String) {
        val manager = supportFragmentManager
        progressDialog = ProgressDialogFragment.newInstance(title, message)
        progressDialog.isCancelable = false
        progressDialog.show(manager, "progress")
    }

    fun stopShowingLoading() {
        progressDialog.dismiss()
    }

    fun toast(text : String){
        Toast.makeText(baseContext, text, Toast.LENGTH_LONG).show()
    }



}
