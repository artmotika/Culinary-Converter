package com.example.converterapp

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.adapter.MealAdapter
import com.example.converterapp.model.PageItem


class MealSetting : AppCompatActivity() {
    private var isGram = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_setting)

        val mealId = intent.getIntExtra("mealId", 0)
        val settingTextMeal: TextView = findViewById(R.id.settingTextMeal)
        settingTextMeal.textSize = 15.0f
        settingTextMeal.text = "gram"
        val editNumberMeal: EditText = findViewById(R.id.editNumberMeal)
        editNumberMeal.setText("100")
        setMealRecycler(volumeArray, mealId, toEditNumber(editNumberMeal.text.toString()))

        val switchWeight: Switch = findViewById(R.id.switchWeight)

        val buttonStates = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ), intArrayOf(
                Color.GREEN,
                Color.GREEN,
                Color.BLUE
            )
        )
        switchWeight.thumbDrawable.setTintList(buttonStates)
        switchWeight.trackDrawable.setTintList(buttonStates)

        switchWeight.setOnCheckedChangeListener { _, isChecked ->
            isGram = isChecked
            if(isChecked){
                settingTextMeal.text = "gram"
                settingTextMeal.textSize = 14.0f
                setMealRecycler(volumeArray, mealId, toEditNumber(editNumberMeal.text.toString()))
            }else{
                settingTextMeal.text = "ml"
                settingTextMeal.textSize = 17.0f
                setMealRecycler(weightArray, mealId, toEditNumber(editNumberMeal.text.toString()))
            }
        }

        editNumberMeal.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // Fires right as the text is being changed (even supplies the range of text)
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // Fires right before text is changing
                }

                override fun afterTextChanged(s: Editable) {
                    // Fires right after the text has changed
                    if (isGram) setMealRecycler(volumeArray, mealId, toEditNumber(editNumberMeal.text.toString()))
                    else setMealRecycler(weightArray, mealId, toEditNumber(editNumberMeal.text.toString()))
                }
            }
        )
    }

    private fun setMealRecycler(pageArray: Array<PageItem>, mealId: Int, editNumber: Double) {
        val layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        val mealRecycler: RecyclerView = findViewById(R.id.mealRecycler)
        mealRecycler.layoutManager = layoutManager
        mealRecycler.adapter = MealAdapter(this, pageArray, isGram, mealId, editNumber)
    }

    private fun toEditNumber(EditString: String): Double {
        if (EditString != "") return EditString.toDouble()
        return 100.0
    }
}