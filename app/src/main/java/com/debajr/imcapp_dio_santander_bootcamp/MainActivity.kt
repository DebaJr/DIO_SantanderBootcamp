package com.debajr.imcapp_dio_santander_bootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

const val INCHES_TO_METER = 0.0254f
const val FEET_TO_METER = 12 * INCHES_TO_METER
const val STONE_TO_KG = 6.35029f

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

        heightRadioGroup?.setOnCheckedChangeListener { _, id ->
            heightUnitCheck(id)
        }

        weightRadioGroup?.setOnCheckedChangeListener { _, id ->
            weightUnitCheck(id)
        }

        calcBTN?.setOnClickListener {
            val height = getHeight()
            val weight = getWeight()

            val imc = weight / (height * height)

            classify(imc)
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

    private fun getHeight() : Float {
        if (height_feet_Group.visibility == View.VISIBLE){
            val feet = height_feet_ET?.text.toString().toFloatOrNull()
            val inches = height_inches_ET?.text.toString().toFloatOrNull()
            if(feet != null && inches != null) {
                return (feet * FEET_TO_METER) + (inches * INCHES_TO_METER)
            }
            else return 1f

        }
        else {
            val meters = height_meters_ET?.text.toString().toFloatOrNull()
            if (meters != null) return meters
            else return 1f
        }
    }

    private fun getWeight() : Float {
        if (weight_stones_Group.visibility == View.VISIBLE) {
            val weight = weight_stones_ET?.text.toString().toFloatOrNull()
            if (weight != null) return weight * STONE_TO_KG
            else return 1f
        }
        else {
            val weight = weight_kg_ET?.text.toString().toFloatOrNull()
            if (weight != null) return weight
            else return 1f
        }
    }

    private fun classify(imc : Float) {
        when {
            imc < 17.0 -> {
                imc_bmiTV.setTextColor(ContextCompat.getColor(this, R.color.design_default_color_error))
                getString(R.string.extreme_low_weight)
                val text = "IMC : $imc\n${getString(R.string.extreme_low_weight)}"
                imc_bmiTV.text = text
            }
            imc < 18.5 -> {
                imc_bmiTV.setTextColor(ContextCompat.getColor(this, R.color.low_weight))
                val text = "IMC : $imc\n${getString(R.string.low_weight_msg)}"
                imc_bmiTV.text = text
            }
            imc < 25.0 -> {
                imc_bmiTV.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                val text = "IMC : $imc\n${getString(R.string.normal_weight)}"
                imc_bmiTV.text = text
            }
            imc < 30 -> {
                imc_bmiTV.setTextColor(ContextCompat.getColor(this, R.color.over_weight))
                val text = "IMC : $imc\n${getString(R.string.overweight)}"
                imc_bmiTV.text = text
            }
            imc < 35 -> {
                imc_bmiTV.setTextColor(ContextCompat.getColor(this, R.color.obeseI))
                val text = "IMC : $imc\n${getString(R.string.obeseI)}"
                imc_bmiTV.text = text
            }
            imc < 40 -> {
                imc_bmiTV.setTextColor(ContextCompat.getColor(this, R.color.obeseII))
                val text = "IMC : $imc\n${getString(R.string.obeseII)}"
                imc_bmiTV.text = text
            }
            else -> {
                imc_bmiTV.setTextColor(ContextCompat.getColor(this, R.color.obeseIII))
                val text = "IMC : $imc\n${getString(R.string.obeseIII)}"
                imc_bmiTV.text = text
            }
        }
    }
}