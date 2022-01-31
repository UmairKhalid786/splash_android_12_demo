package com.techlad.splashscreenapidemo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.techlad.splashscreenapidemo.ui.theme.SplashScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel: HomeViewModel by viewModels()

        installSplashScreen().apply {
            
            // Conditional visibility part
            setKeepVisibleCondition {
                // It will wait for API to conclude
                // It will keep its visibility until the condition remains true
                !homeViewModel.apiResponse.value
            }

            // Animation part
            setOnExitAnimationListener { sp ->
                // Create your custom animation.
                sp.iconView.animate().rotation(180F).duration = 3000L
                val slideUp = ObjectAnimator.ofFloat(
                    sp.iconView,
                    View.TRANSLATION_Y,
                    0f,
                    -sp.iconView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 3000L

                // Call SplashScreenView.remove at the end of your custom animation.
                slideUp.doOnEnd { sp.remove() }

                // Run your animation.
                slideUp.start()
            }
        }

        /*...... Set Content and Rest of the app screen initialization
                 stuff behind this line will go on ....*/

        setContent {
            SplashScreenTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = getString(R.string.hello_world))
                }
            }
        }
    }
}