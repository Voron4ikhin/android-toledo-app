package toledo24.pro.presentation.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.ActivityWelcomeBinding
import toledo24.pro.presentation.MainActivity
import toledo24.pro.presentation.autorization.AutorizationActivity


class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    private val vm by viewModel<WelcomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentMain = Intent(this, MainActivity::class.java)
        val intentAuth = Intent(this, AutorizationActivity::class.java)

        lifecycleScope.launchWhenStarted {
            vm.showAuthActivity.onEach { value ->
                if(value) startActivity(intentMain)
                else startActivity(intentAuth)
            }.collect()
        }
    }

}