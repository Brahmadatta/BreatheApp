package com.example.breatheapp

import android.content.Intent
import android.icu.text.MessageFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.annotation.RequiresApi
import com.example.breatheapp.Util.Prefs
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var prefs : Prefs

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = Prefs(this)


        breathsTakenTxt.text = MessageFormat.format("{0} min today",prefs.sessions)
        todayMinusTxt.text = MessageFormat.format("{0} breaths",prefs.breaths)
        lastBreathsTxt.text = prefs.date

        startIntroAnimation()

        startButton.setOnClickListener {

            startAnimation()
        }

    }

    private fun startAnimation()
    {
        ViewAnimator.animate(lotusImage)
            .alpha(0f,1f)
            .onStart {
                guideTxt.text = "Inhale...Exhale"
            }
            .decelerate()
            .duration(1000)
            .thenAnimate(lotusImage)
            .scale(0.02f,1.5f,0.02f)
            .rotation(360f)
            .repeatCount(5)
            .accelerate()
            .duration(5000)
            .onStop {
                guideTxt.text = "Good Job"
                lotusImage.scaleX = 1.0f
                lotusImage.scaleY = 1.0f


                prefs.sessions = prefs.sessions + 1
                prefs.breaths = prefs.breaths + 1
                prefs.setDate(System.currentTimeMillis())

                val handler = Handler()
                val countDownTimer = Runnable {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                handler.postDelayed(countDownTimer,100)

                //refresh the Activity
//                object : CountDownTimer(2000,1000)
//                {
//                    override fun onTick(p0: Long) {
//
//                    }
//
//                    override fun onFinish() {
//
//                        startActivity(Intent(applicationContext,MainActivity::class.java))
//                        finish()
//                    }
//
//                }.start() // for countdown timer
            }
            .start() // for animation
    }

    private fun startIntroAnimation()
    {

        ViewAnimator.animate(guideTxt)
            .scale(0f,1f)
            .duration(1500)
            .onStart {
                guideTxt.text = "Breathe"
            }.start()

    }
}