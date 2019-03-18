package com.outsmart.atlantica_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.outsmart.atlanticadesign.Atoms.ATLDivider
import com.outsmart.atlanticadesign.Molecules.ATLSettingsItem
import com.outsmart.atlanticadesign.enums.TextAlignment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_linear)
        ATLSettingsItem(this).create()
            .setText("Test")
            .setRightComponentVariant(ATLSettingsItem.RightComponentVariant.SWITCH)
            .setIcon(R.drawable.ic_launcher_background)

        ATLDivider(this).create()

        ATLSettingsItem(this).create()
            .setText("Test2")
            .setRightComponentVariant(ATLSettingsItem.RightComponentVariant.ICON)
            .setRightIcon(R.drawable.ic_launcher_background)

        ATLDivider(this).create()

        ATLSettingsItem(this).create()
            .setText("Test3 - CenteredText")
            .setRightComponentVariant(ATLSettingsItem.RightComponentVariant.SWITCH)
            .setIcon(R.drawable.ic_launcher_background)
            .setStyle {
                it.apply {
                    it.marginLeftSpacing = 100
                    it.marginRightSpacing = 100
                    it.textAlignment = TextAlignment.CENTER
                }
            }
            .setOnToggle { id, selected ->
                Toast.makeText(this, "Toggled item: $selected", Toast.LENGTH_SHORT).show()
            }
            .setOnSelectItem { id ->
                Toast.makeText(this, "Selected item", Toast.LENGTH_SHORT).show()
            }

        ATLDivider(this).create()
    }
}