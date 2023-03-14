package com.zakariya.coachmarkdemo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.android.library.coachmark.components.CoachMarkInfo
import com.android.library.coachmark.components.CoachMarkOverlay
import com.android.library.coachmark.components.CoachMarkSkipButton
import com.android.library.coachmark.configuration.CoachMarkConfig
import com.android.library.coachmark.configuration.CoachMarkSequence
import com.android.library.coachmark.utility.TypeFace
import com.zakariya.coachmarkdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnHello.setOnClickListener {
            val coachMarkSequence = CoachMarkSequence(this)
            val coachMarkBuilder = CoachMarkOverlay.Builder(this)
                .setOverlayTargetView(binding.btnHello)
                .setInfoViewBuilder(
                    CoachMarkInfo.Builder(this)
                        .setInfoText("It's a hello button")
                        .setMargin(50, 30, 50, 30)
                )
                .setSkipButtonBuilder(
                    CoachMarkSkipButton.Builder(this)
                        .setMargin(10, 30,0,0)
                        .setButtonClickListener(object : CoachMarkSkipButton.ButtonClickListener {
                            override fun onSkipButtonClick(view: View) {
                                (window.decorView as ViewGroup).removeView(view)
                                coachMarkSequence.clearList()
                            }
                        })
                )

            coachMarkSequence.addItem(coachMarkBuilder, true)

            val coachMarkConfig = CoachMarkConfig(this)
            MarkData.data.forEach {
                val resId = resources.getIdentifier(it.id,"id",packageName )
                val btn = findViewById<View>(resId)
                addCoachMarkSeq(btn, it.info, coachMarkConfig, coachMarkSequence)
            }
            coachMarkConfig.setCoachMarkToolTipColor(Color.CYAN)
//            addCoachMarkSeq(binding.btn1, "Button 1", coachMarkConfig, coachMarkSequence)

            coachMarkSequence.start(window?.decorView as ViewGroup)
        }

    }

    private fun addCoachMarkSeq(
        view: View,
        infoTxt: String,
        config: CoachMarkConfig,
        seq: CoachMarkSequence
    ) {
        config.setInfoTextMargin(20, 20, 20, 0)
        seq.setSequenceConfig(config)
        config.setInfoTextTypeface(TypeFace.BOLD)
        seq.addItem(view, infoTxt)
    }
}