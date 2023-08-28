package ci.orange.sicdomotique

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalContext
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import com.google.accompanist.themeadapter.material.MdcTheme
//import com.thingclips.smart.api.router.UrlRouter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContent {
            SICAutomationTheme {
                MainNavigation()
            }
        }
//        findViewById<Button>(R.id.btn_start_home).setOnClickListener {
//            try {
//                UrlRouter.execute(UrlRouter.makeBuilder(this, "family_manage"))
//            }catch (e:Exception){
//                Log.e("MainActivity", "onCreate: $e", )
//            }
//
//        }


    }


}