package com.debajr.imcapp_dio_santander_bootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightRadioGroup :RadioGroup? = height_units_RGroup
        val weightRadioGroup :RadioGroup? = weight_units_RGroup

        if (heightRadioGroup != null) {
            heightUnitCheck(heightRadioGroup.checkedRadioButtonId)
        }

        if (weightRadioGroup != null) {
            weightUnitCheck(weightRadioGroup.checkedRadioButtonId)
        }

        heightRadioGroup?.setOnCheckedChangeListener { group, id ->
            heightUnitCheck(id)
        }

        weightRadioGroup?.setOnCheckedChangeListener { group, id ->
            weightUnitCheck(id)
        }
    }

    private fun heightUnitCheck(id : Int){
        val radioBtn = findViewById<RadioButton>(id)
        when (radioBtn.text.toString()) {
            resources.getString(R.string.feet) -> {
                height_feet_Group.visibility = View.VISIBLE
                height_meters_Group.visibility = View.GONE
            }
            resources.getString(R.string.meters) -> {
                height_feet_Group.visibility = View.GONE
                height_meters_Group.visibility = View.VISIBLE
            }
        }
    }

    private fun weightUnitCheck(id : Int){
        val radioBtn = findViewById<RadioButton>(id)
        when (radioBtn.text.toString()) {
            resources.getString(R.string.stones) -> {
                weight_stones_Group.visibility = View.VISIBLE
                weight_kg_Group.visibility = View.GONE
            }
            resources.getString(R.string.kilogram) -> {
                weight_stones_Group.visibility = View.GONE
                weight_kg_Group.visibility = View.VISIBLE
            }
        }
    }
}