package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Locale


class MainActivity : Activity() {
    var t1: TextToSpeech? = null
    var ed1: EditText? = null
    var b1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed1 = findViewById<View>(R.id.editText) as EditText
        b1 = findViewById<View>(R.id.button) as Button

        t1 = TextToSpeech(
            applicationContext
        ) { status ->
            if (status != TextToSpeech.ERROR) {
                t1!!.setLanguage(Locale.UK)
            }
        }

        b1!!.setOnClickListener {
            val toSpeak = ed1!!.text.toString()
            Toast.makeText(applicationContext, toSpeak, Toast.LENGTH_SHORT).show()
            t1!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    public override fun onPause() {
        if (t1 != null) {
            t1!!.stop()
            t1!!.shutdown()
        }
        super.onPause()
    }
}