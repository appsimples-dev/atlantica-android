package com.outsmart.atlantica_android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.outsmart.atlanticadesign.Atoms.ATLSimpleLabel
import com.outsmart.atlanticadesign.Molecules.ATLSettingsItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_linear)
        ATLSettingsItem(this).create(80f)
            .setText("Test")
            .setRightComponentVariant(ATLSettingsItem.RightComponentVariant.SWITCH)
            .setIcon(R.drawable.ic_launcher_background)
            .requestLayout()

        ATLSettingsItem(this).create(60f)
            .setText("Test2")
            .setRightComponentVariant(ATLSettingsItem.RightComponentVariant.ICON)
            .setRightIcon(R.drawable.ic_launcher_background)
            .requestLayout()

        ATLSettingsItem(this).create(60f)
            .setText("Test3 - CenteredText")
            .setRightComponentVariant(ATLSettingsItem.RightComponentVariant.SWITCH)
            .setTextAlignment(ATLSimpleLabel.TextAlignment.CENTER)
            .setIcon(R.drawable.ic_launcher_background)
            .requestLayout()
    }
}
