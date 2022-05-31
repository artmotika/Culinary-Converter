package com.example.converterapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.converterapp.model.PageItem


val volumeArray = arrayOf(
    PageItem(0, "ml", R.drawable.ml),
    PageItem(1, "tablespoon", R.drawable.tablespoon),
    PageItem(2, "dessertspoon", R.drawable.dessertspoon),
    PageItem(3, "teaspoon", R.drawable.teaspoon),
    PageItem(4, "glass", R.drawable.glass),
    PageItem(5, "cup(metric)", R.drawable.cupmetric),
    PageItem(6, "cup(Us)", R.drawable.cupus),
    PageItem(7, "cup(Uk)", R.drawable.cupuk),
    PageItem(8, "floz(Us)", R.drawable.flozus),
    PageItem(9, "floz(Uk)", R.drawable.flozuk),
    PageItem(10, "pt(Us)", R.drawable.ptus),
    PageItem(11, "pt(Uk)", R.drawable.ptuk),
)

val weightArray = arrayOf(
    PageItem(0, "gram", R.drawable.gram),
    PageItem(1, "tablespoon", R.drawable.tablespoon),
    PageItem(2, "dessertspoon", R.drawable.dessertspoon),
    PageItem(3, "teaspoon", R.drawable.teaspoon),
    PageItem(4, "glass", R.drawable.glass),
    PageItem(5, "cup(metric)", R.drawable.cupmetric),
    PageItem(6, "cup(Us)", R.drawable.cupus),
    PageItem(7, "cup(Uk)", R.drawable.cupuk),
    PageItem(8, "floz(Us)", R.drawable.flozus),
    PageItem(9, "floz(Uk)", R.drawable.flozuk),
    PageItem(10, "pt(Us)", R.drawable.ptus),
    PageItem(11, "pt(Uk)", R.drawable.ptuk),
)

val volumeRussianTextArray = arrayOf(
    "Миллилитр",
    "Столовая ложка",
    "Десертная ложка",
    "Чайная ложка",
    "Стакан",
    "Метрическая чашка",
    "Американская чашка",
    "Английская чашка",
    "Жидкая унция США",
    "Жидкая унция Англия",
    "Пинта США",
    "Пинта Англия"
)

val weightRussianTextArray = arrayOf(
    "Грамм",
    "Столовая ложка",
    "Десертная ложка",
    "Чайная ложка",
    "Стакан",
    "Метрическая чашка",
    "Американская чашка",
    "Английская чашка",
    "Жидкая унция США",
    "Жидкая унция Англия",
    "Пинта США",
    "Пинта Англия"
)

val volumeShortNamesArray = arrayOf(
    "ml",
    "table\nspoon",
    "dessert\nspoon",
    "tea\nspoon",
    "glass",
    "cup\n(metric)",
    "cup\n(Us)",
    "cup\n(Uk)",
    "floz\n(Us)",
    "floz\n(Uk)",
    "pt\n(Us)",
    "pt\n(Uk)"
)

val weightShortNamesArray = arrayOf(
    "gram",
    "table\nspoon",
    "dessert\nspoon",
    "tea\nspoon",
    "glass",
    "cup\n(metric)",
    "cup\n(Us)",
    "cup\n(Uk)",
    "floz\n(Us)",
    "floz\n(Uk)",
    "pt\n(Us)",
    "pt\n(Uk)"
)

class MainActivity : AppCompatActivity() {

    private val INIT_TIME_TIMER = 0L
    private val ADD_TIME_TIMER = 60000L // 1 min
    private lateinit var timer: CountDownTimer
    private lateinit var textTimer: TextView
    private var timeLeftInMiliseconds = INIT_TIME_TIMER
    private var timerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bellSound: MediaPlayer = MediaPlayer.create(this, R.raw.bell_sound)
        val cancelSound: MediaPlayer = MediaPlayer.create(this, R.raw.cancel_sound)

        textTimer = findViewById(R.id.textTimer)
        val buttonTimerStart: ImageButton = findViewById(R.id.buttonTimerStart)
        buttonTimerStart.setOnClickListener {
            val toast = Toast.makeText(this, "Таймер + 1 минута", Toast.LENGTH_SHORT)
            toast.show()
            startTimerAndAdd(this)
            soundPlay(bellSound)
            toast.cancel()
        }

        val buttonTimerReset: ImageButton = findViewById(R.id.buttonTimerReset)
        buttonTimerReset.setOnClickListener {
            if (timerRunning) {
                val toast = Toast.makeText(this, "Таймер сброшен", Toast.LENGTH_SHORT)
                toast.show()
                resetTimer()
                soundPlay(cancelSound)
            }
        }

        val buttonSelectionVolumes: ImageButton = findViewById(R.id.imageVolume)
        buttonSelectionVolumes.setOnClickListener {
            val intent = Intent(this, VolumeSelection::class.java)
            this.startActivity(intent)
        }
        val buttonSelectionPeanut: ImageButton = findViewById(R.id.imagePeanut)
        buttonSelectionPeanut.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 1)
            this.startActivity(intent)
        }

        val buttonSelectionJam: ImageButton = findViewById(R.id.image_Jam)
        buttonSelectionJam.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 2)
            this.startActivity(intent)
        }

        val buttonSelectionCherry: ImageButton = findViewById(R.id.image_Cherry)
        buttonSelectionCherry.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 3)
            this.startActivity(intent)
        }

        val buttonSelectionWater: ImageButton = findViewById(R.id.image_Water)
        buttonSelectionWater.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 4)
            this.startActivity(intent)
        }

        val buttonSelectionVodka: ImageButton = findViewById(R.id.image_Vodka)
        buttonSelectionVodka.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 5)
            this.startActivity(intent)
        }

        val buttonSelectionPeas: ImageButton = findViewById(R.id.image_Peas)
        buttonSelectionPeas.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 6)
            this.startActivity(intent)
        }

        val buttonSelectionWalnut: ImageButton = findViewById(R.id.image_Walnut)
        buttonSelectionWalnut.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 7)
            this.startActivity(intent)
        }

        val buttonSelectionDryYeast: ImageButton = findViewById(R.id.image_Dry_Yeast)
        buttonSelectionDryYeast.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 8)
            this.startActivity(intent)
        }

        val buttonSelectionGelatinPowder: ImageButton = findViewById(R.id.image_Gelatin_Powder)
        buttonSelectionGelatinPowder.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 9)
            this.startActivity(intent)
        }

        val buttonSelectionRaisin: ImageButton = findViewById(R.id.image_Raisin)
        buttonSelectionRaisin.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 10)
            this.startActivity(intent)
        }

        val buttonSelectionCocoaPowder: ImageButton = findViewById(R.id.image_Cocoa_Powder)
        buttonSelectionCocoaPowder.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 11)
            this.startActivity(intent)
        }

        val buttonSelectionKefir: ImageButton = findViewById(R.id.image_Kefir)
        buttonSelectionKefir.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 12)
            this.startActivity(intent)
        }

        val buttonSelectionStrawberry: ImageButton = findViewById(R.id.image_Strawberry)
        buttonSelectionStrawberry.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 13)
            this.startActivity(intent)
        }

        val buttonSelectionCoconutCrumbs: ImageButton = findViewById(R.id.image_Coconut_Crumbs)
        buttonSelectionCoconutCrumbs.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 14)
            this.startActivity(intent)
        }

        val buttonSelectionRoastedCoffeeBeans: ImageButton = findViewById(R.id.image_Roasted_Coffee_Beans)
        buttonSelectionRoastedCoffeeBeans.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 15)
            this.startActivity(intent)
        }

        val buttonSelectionPotatoStarch: ImageButton = findViewById(R.id.image_Potato_Starch)
        buttonSelectionPotatoStarch.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 16)
            this.startActivity(intent)
        }

        val buttonSelectionHerculeanGrits: ImageButton = findViewById(R.id.image_Herculean_Grits)
        buttonSelectionHerculeanGrits.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 17)
            this.startActivity(intent)
        }

        val buttonSelectionBuckwheatGroats: ImageButton = findViewById(R.id.image_Buckwheat_Groats)
        buttonSelectionBuckwheatGroats.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 18)
            this.startActivity(intent)
        }

        val buttonSelectionSemolina: ImageButton = findViewById(R.id.image_Semolina)
        buttonSelectionSemolina.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 19)
            this.startActivity(intent)
        }

        val buttonSelectionPearlBarley: ImageButton = findViewById(R.id.image_Pearl_Barley)
        buttonSelectionPearlBarley.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 20)
            this.startActivity(intent)
        }

        val buttonSelectionMilletGroats: ImageButton = findViewById(R.id.image_Millet_Groats)
        buttonSelectionMilletGroats.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 21)
            this.startActivity(intent)
        }

        val buttonSelectionCorn: ImageButton = findViewById(R.id.image_Corn)
        buttonSelectionCorn.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 22)
            this.startActivity(intent)
        }

        val buttonSelectionCornSticks: ImageButton = findViewById(R.id.image_Corn_Sticks)
        buttonSelectionCornSticks.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 23)
            this.startActivity(intent)
        }

        val buttonSelectionMayonnaise: ImageButton = findViewById(R.id.image_Mayonnaise)
        buttonSelectionMayonnaise.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 24)
            this.startActivity(intent)
        }

        val buttonSelectionPasta: ImageButton = findViewById(R.id.image_Pasta)
        buttonSelectionPasta.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 25)
            this.startActivity(intent)
        }

        val buttonSelectionRaspberry: ImageButton = findViewById(R.id.image_Raspberry)
        buttonSelectionRaspberry.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 26)
            this.startActivity(intent)
        }

        val buttonSelectionOliveOil: ImageButton = findViewById(R.id.image_Olive_Oil)
        buttonSelectionOliveOil.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 27)
            this.startActivity(intent)
        }

        val buttonSelectionSunflowerOil: ImageButton = findViewById(R.id.image_Sunflower_Oil)
        buttonSelectionSunflowerOil.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 28)
            this.startActivity(intent)
        }

        val buttonSelectionMeltedButter: ImageButton = findViewById(R.id.image_Melted_Butter)
        buttonSelectionMeltedButter.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 29)
            this.startActivity(intent)
        }

        val buttonSelectionHoney: ImageButton = findViewById(R.id.image_Honey)
        buttonSelectionHoney.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 30)
            this.startActivity(intent)
        }

        val buttonSelectionAlmond: ImageButton = findViewById(R.id.image_Almond)
        buttonSelectionAlmond.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 31)
            this.startActivity(intent)
        }

        val buttonSelectionMilk: ImageButton = findViewById(R.id.image_Milk)
        buttonSelectionMilk.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 32)
            this.startActivity(intent)
        }

        val buttonSelectionCondensedMilk: ImageButton = findViewById(R.id.image_Condensed_Milk)
        buttonSelectionCondensedMilk.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 33)
            this.startActivity(intent)
        }

        val buttonSelectionMilkPowder: ImageButton = findViewById(R.id.image_Milk_Powder)
        buttonSelectionMilkPowder.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 34)
            this.startActivity(intent)
        }

        val buttonSelectionCornFlour: ImageButton = findViewById(R.id.image_Corn_Flour)
        buttonSelectionCornFlour.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 35)
            this.startActivity(intent)
        }

        val buttonSelectionWheatFlour: ImageButton = findViewById(R.id.image_Wheat_Flour)
        buttonSelectionWheatFlour.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 36)
            this.startActivity(intent)
        }

        val buttonSelectionRyeFlour: ImageButton = findViewById(R.id.image_Rye_Flour)
        buttonSelectionRyeFlour.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 37)
            this.startActivity(intent)
        }

        val buttonSelectionOats: ImageButton = findViewById(R.id.image_Oats)
        buttonSelectionOats.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 38)
            this.startActivity(intent)
        }

        val buttonSelectionBran: ImageButton = findViewById(R.id.image_Bran)
        buttonSelectionBran.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 39)
            this.startActivity(intent)
        }

        val buttonSelectionBeer: ImageButton = findViewById(R.id.image_Beer)
        buttonSelectionBeer.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 40)
            this.startActivity(intent)
        }

        val buttonSelectionPopcorn: ImageButton = findViewById(R.id.image_Popcorn)
        buttonSelectionPopcorn.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 41)
            this.startActivity(intent)
        }

        val buttonSelectionBakingPowderDough: ImageButton = findViewById(R.id.image_Baking_Powder_Dough)
        buttonSelectionBakingPowderDough.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 42)
            this.startActivity(intent)
        }

        val buttonSelectionRice: ImageButton = findViewById(R.id.image_Rice)
        buttonSelectionRice.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 43)
            this.startActivity(intent)
        }

        val buttonSelectionCaneSugar: ImageButton = findViewById(R.id.image_Cane_Sugar)
        buttonSelectionCaneSugar.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 44)
            this.startActivity(intent)
        }

        val buttonSelectionPowderedSugar: ImageButton = findViewById(R.id.image_Powdered_Sugar)
        buttonSelectionPowderedSugar.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 45)
            this.startActivity(intent)
        }

        val buttonSelectionGranulatedSugar: ImageButton = findViewById(R.id.image_Granulated_Sugar)
        buttonSelectionGranulatedSugar.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 46)
            this.startActivity(intent)
        }

        val buttonSelectionSunflowerSeedsFried: ImageButton = findViewById(R.id.image_Sunflower_Seeds_Fried)
        buttonSelectionSunflowerSeedsFried.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 47)
            this.startActivity(intent)
        }

        val buttonSelectionRoastedPumpkinSeeds: ImageButton = findViewById(R.id.image_Roasted_Pumpkin_Seeds)
        buttonSelectionRoastedPumpkinSeeds.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 48)
            this.startActivity(intent)
        }

        val buttonSelectionCream: ImageButton = findViewById(R.id.image_Cream)
        buttonSelectionCream.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 49)
            this.startActivity(intent)
        }

        val buttonSelectionSourCream: ImageButton = findViewById(R.id.image_Sour_Cream)
        buttonSelectionSourCream.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 50)
            this.startActivity(intent)
        }

        val buttonSelectionBakingSoda: ImageButton = findViewById(R.id.image_Baking_Soda)
        buttonSelectionBakingSoda.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 51)
            this.startActivity(intent)
        }

        val buttonSelectionFinelyGroundSalt: ImageButton = findViewById(R.id.image_Finely_Ground_Salt)
        buttonSelectionFinelyGroundSalt.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 52)
            this.startActivity(intent)
        }

        val buttonSelectionGroundCrackers: ImageButton = findViewById(R.id.image_Ground_Crackers)
        buttonSelectionGroundCrackers.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 53)
            this.startActivity(intent)
        }

        val buttonSelectionTomatoPaste: ImageButton = findViewById(R.id.image_Tomato_Paste)
        buttonSelectionTomatoPaste.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 54)
            this.startActivity(intent)
        }

        val buttonSelectionVinegar: ImageButton = findViewById(R.id.image_Vinegar)
        buttonSelectionVinegar.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId",  55)
            this.startActivity(intent)
        }

        val buttonSelectionBeans: ImageButton = findViewById(R.id.image_Beans)
        buttonSelectionBeans.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 56)
            this.startActivity(intent)
        }

        val buttonSelectionHazelnuts: ImageButton = findViewById(R.id.image_Hazelnuts)
        buttonSelectionHazelnuts.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 57)
            this.startActivity(intent)
        }

        val buttonSelectionCornFlakes: ImageButton = findViewById(R.id.image_Corn_Flakes)
        buttonSelectionCornFlakes.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 58)
            this.startActivity(intent)
        }

        val buttonSelectionOatFlakes: ImageButton = findViewById(R.id.image_Oat_Flakes)
        buttonSelectionOatFlakes.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 59)
            this.startActivity(intent)
        }

        val buttonSelectionBlackCurrant: ImageButton = findViewById(R.id.image_Black_Currant)
        buttonSelectionBlackCurrant.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 60)
            this.startActivity(intent)
        }

        val buttonSelectionDriedBlueberries: ImageButton = findViewById(R.id.image_Dried_Blueberries)
        buttonSelectionDriedBlueberries.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 61)
            this.startActivity(intent)
        }

        val buttonSelectionLentils: ImageButton = findViewById(R.id.image_Lentils)
        buttonSelectionLentils.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 62)
            this.startActivity(intent)
        }

        val buttonSelectionChips: ImageButton = findViewById(R.id.image_Chips)
        buttonSelectionChips.setOnClickListener {
            val intent = Intent(this, MealSetting::class.java)
            intent.putExtra("mealId", 63)
            this.startActivity(intent)
        }
    }

    fun soundPlay(sound: MediaPlayer) {
        sound.start()
    }

    fun soundStop(sound: MediaPlayer) {
        sound.reset()
    }

    fun startTimerAndAdd(ctx: Context) {
        val newTime = if (timeLeftInMiliseconds <= 3480000) timeLeftInMiliseconds + ADD_TIME_TIMER
        else 3600000L
        if (timerRunning) resetTimer()
        timeLeftInMiliseconds = newTime
        startTimer(ctx)
    }

    fun startTimer(ctx: Context) {
        timer = object : CountDownTimer(timeLeftInMiliseconds, 1000) {
            override fun onTick(l: Long) {
                timeLeftInMiliseconds = l
                updateTimer()
            }

            override fun onFinish() {
                timerRunning = false
                updateTimer()
                showTimerDialog(ctx)
            }
        }.start()
        timerRunning = true
    }

    fun showTimerDialog(ctx: Context) {
        val dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.timer_dialog)
        val ringSound: MediaPlayer = MediaPlayer.create(this, R.raw.ring)
        soundPlay(ringSound)
        val buttonEndTimer: ImageButton = dialog.findViewById(R.id.buttonEndTimer)
        buttonEndTimer.setOnClickListener {
            dialog.dismiss()
            soundStop(ringSound)
        }
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.show()
        dialog.window?.attributes = lp
    }

    fun resetTimer() {
        timer.cancel()
        timeLeftInMiliseconds = INIT_TIME_TIMER
        timerRunning = false
        updateTimer()
    }

    fun updateTimer() {
        val minutes = timeLeftInMiliseconds.toInt() / 60000
        val seconds = timeLeftInMiliseconds.toInt() % 60000 / 1000
        var timeLeftText = "$minutes:"
        if (seconds < 10) timeLeftText += "0"
        timeLeftText += seconds
        if (minutes == 0 && seconds == 0) textTimer.text = ""
        else textTimer.text = timeLeftText
    }
}