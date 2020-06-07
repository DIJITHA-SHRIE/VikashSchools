package com.example.vedanam.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.vedanam.Model.LogInInputModel
import com.example.vedanam.Model.LogInModel
import com.example.vedanam.R
import com.example.vedanam.Repository.LogInRepository
import com.example.vedanam.ViewModel.LogInViewModel
import com.example.vedanam.databinding.ActivityLogInBinding
import com.example.vedanam.getNetworkService
import com.google.android.material.snackbar.Snackbar


class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    lateinit var userName:String
    lateinit var userPassword:String
    private val PREF_NAME = "Vikash_SP"
    private val USER_KEY = "user_key"
    private val USER_VALUE = "user_value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_log_in
        )
        binding.setLifecycleOwner(this)

        val repository = LogInRepository(getNetworkService())
        val viewModel = ViewModelProviders.of(this,LogInViewModel.FACTORY(repository)).get(LogInViewModel::class.java)
        val sharedPreferences:SharedPreferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


        viewModel.mutableLogInLiveData.observe(this) { value ->
            value?.let {
                val logInresponse: LogInModel = value
                if (logInresponse.message == null){
                var userKind: String = ""
                var userValue: String = ""
                if (logInresponse.admin_id != null) {
                    userKind = resources.getString(R.string.admin)
                    userValue = logInresponse.admin_id

                } else if (logInresponse.parent_id != null) {
                    userKind = resources.getString(R.string.parent)
                    userValue = logInresponse.parent_id
                } else if (logInresponse.teacher_id != null) {
                    userKind = resources.getString(R.string.teacher)
                    userValue = logInresponse.teacher_id

                }

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(USER_KEY, userKind)
                editor.putString(USER_VALUE, userValue)
                editor.apply()
                editor.commit()


                if (logInresponse.email != null) {
                    val intent = Intent(applicationContext, NavigationActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

                else{
                    Snackbar.make(binding.loginRelative,logInresponse.message,Snackbar.LENGTH_LONG).show()

                }

            }
        }

        binding.loginButton.setOnClickListener(View.OnClickListener {

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.getWindowToken(), 0)

            userName = binding.emailEditText.text.toString()
            userPassword = binding.passwordEditText.text.toString()

            Log.i("UserCredentials","$userName-$userPassword")

            if(!userName.isEmpty() && !userPassword.isEmpty()){

            viewModel.onLogIn(LogInInputModel("login",userName,userPassword))}
            else{
                Snackbar.make(it,"Please enter credentials",Snackbar.LENGTH_LONG).show()

            }


        })
    }
}
