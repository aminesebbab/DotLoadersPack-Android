package com.agrawalsuneet.loaders

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import com.agrawalsuneet.dotsloader.ui.CircularDotsLoader
import com.agrawalsuneet.dotsloader.ui.LazyLoader
import com.agrawalsuneet.dotsloader.ui.LinearDotsLoader
import com.agrawalsuneet.dotsloader.ui.TashieLoader
import com.agrawalsuneet.loaders.dialog.DotsLoaderDialog

class MainActivity : AppCompatActivity() {

    lateinit var containerLL : LinearLayout

    private var colorSwitch = false

    lateinit var loader: LinearDotsLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        containerLL = findViewById(R.id.container) as LinearLayout

        //initView()
        //initLazyLoader()
        initTashieLoader()
    }

    private fun initTashieLoader() {
        var tashie = TashieLoader(this)
                .apply {
                    noOfDots = 5
                    dotsDist = 10
                    dotsRadius = 30
                    animDuration = 500
                    animDelay = 100
                    dotsColor = resources.getColor(R.color.green)
                    interpolator = LinearInterpolator()
                }
        containerLL.addView(tashie)
    }

    private fun initLazyLoader() {
        var lazyLoader = LazyLoader(this, 15, 5, ContextCompat.getColor(this, R.color.purple_selected))
                .apply {
                    animDuration = 500
                    firstDelayDuration = 100
                    secondDelayDuration = 200
                    interpolator = DecelerateInterpolator()
                }

        /*var lazyLoader = LazyLoader(this).apply{
            animDuration = 500
            firstDelayDuration = 100
            secondDelayDuration = 200
        }
        lazyLoader.dotsRadius = 60
        lazyLoader.dotsDist = 60*/

        containerLL.addView(lazyLoader)
    }

    private fun initView() {
        loader = LinearDotsLoader(this)
        loader.defaultColor = ContextCompat.getColor(this, R.color.loader_defalut)
        loader.selectedColor = ContextCompat.getColor(this, R.color.loader_selected)
        loader.isSingleDir = false
        loader.noOfDots = 5
        loader.selRadius = 60
        loader.expandOnSelect = false
        loader.radius = 40
        loader.dotsDistance = 20
        loader.animDur = 1000
        //loader.firstShadowColor = ContextCompat.getColor(this, R.color.pink_selected)
        //loader.secondShadowColor = ContextCompat.getColor(this, R.color.purple_selected)
        //loader.showRunningShadow = false
        containerLL.addView(loader)


        var cirLoader = CircularDotsLoader(this@MainActivity)
        cirLoader.setPadding(20,20,20,20)
        cirLoader.defaultColor = ContextCompat.getColor(this, R.color.blue_delfault)
        cirLoader.selectedColor = ContextCompat.getColor(this, R.color.blue_selected)
        cirLoader.bigCircleRadius = 116
        cirLoader.radius = 40
        cirLoader.animDur = 100
        //cirLoader.firstShadowColor = ContextCompat.getColor(this, R.color.pink_selected)
        //cirLoader.secondShadowColor = ContextCompat.getColor(this, R.color.purple_selected)
        //cirLoader.showRunningShadow = false

        containerLL.addView(cirLoader)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_dialog -> {
                /*if (colorSwitch) {
                    loader.firstShadowColor = ContextCompat.getColor(this, R.color.pink_selected)
                    loader.secondShadowColor = ContextCompat.getColor(this, R.color.pink_default)
                } else {
                    loader.firstShadowColor = ContextCompat.getColor(this, R.color.purple_selected)
                    loader.secondShadowColor = ContextCompat.getColor(this, R.color.purple_default)
                }
                colorSwitch = !colorSwitch*/
                showAlertDialog();
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showAlertDialog() {
        val dotsDialog = DotsLoaderDialog()
        //dotsDialog.setCancelable(false);
        dotsDialog.show(supportFragmentManager, "dotsDialog")
    }
}
