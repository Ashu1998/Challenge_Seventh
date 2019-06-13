package com.tinkerbyte.challenge_seventh

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shake:Animation = AnimationUtils.loadAnimation(this,R.anim.shake)
        val sharedPref : SharedPreferences = applicationContext.getSharedPreferences("MyPref",Context.MODE_PRIVATE)
        val mEditor: SharedPreferences.Editor = sharedPref.edit()
        mEditor.putString("User","Ashu")
        mEditor.putString("pass","india")
        mEditor.commit()

        Login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val UserName:String = userName.text.toString()
                val password : String = Password.text.toString()

                if(UserName != "" && password != "")
                {
                    if(UserName==sharedPref.getString("User",null)&&password == sharedPref.getString("pass",null))
                    {
                       val mIntent = Intent(this@MainActivity,HomeActivity::class.java)
                        startActivity(mIntent)
                    }
                    else{
                        Toast.makeText(this@MainActivity,"UserName or Password is Wrong",Toast.LENGTH_SHORT).show()
                    }


                }else {

                    if (UserName == "" && password == "") {

                        userName.startAnimation(shake)
                        Password.startAnimation(shake)
                        Toast.makeText(this@MainActivity,"Fields can not be null",Toast.LENGTH_SHORT).show()

                    } else {
                        if (UserName == "") {
                            userName.startAnimation(shake)
                            Toast.makeText(this@MainActivity, "UserName can not be empty", Toast.LENGTH_SHORT).show()

                        } else {
                            Password.startAnimation(shake)
                            Toast.makeText(this@MainActivity, "Password can not be empty", Toast.LENGTH_SHORT).show()
                        }


                    }
                }





            }
        })
    }
}
