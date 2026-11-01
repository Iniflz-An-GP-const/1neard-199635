package shejianguo.app1neard.corpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import shejianguo.app1neard.corpapp.ui.composable.approot.AppRoot
import shejianguo.app1neard.corpapp.ui.theme.Primary
import shejianguo.app1neard.corpapp.ui.theme.SNPCPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Primary.toArgb(), android.graphics.Color.BLACK)
        )
        setContent {
            SNPCPAppTheme {
                AppRoot()
            }
        }
    }
}