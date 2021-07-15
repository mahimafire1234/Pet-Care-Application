package com.mahima.animestreamingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var logo: CircleImageView
    private lateinit var tvtext: TextView
    private lateinit var rel: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logo=findViewById(R.id.logo)
        tvtext=findViewById(R.id.tvtext)
        rel=findViewById(R.id.rel)
        //        for animation

        val animation=AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val animation1=AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        logo.setAnimation(animation)
        tvtext.setAnimation(animation1)




//        using coroutine for splash activity
        CoroutineScope(Dispatchers.Main).launch{
//            suspend for the splash activity
            delay(5000)
//            open intent activity
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
//            finish or destroy the splash screen
            finish()
        }

    }
}

