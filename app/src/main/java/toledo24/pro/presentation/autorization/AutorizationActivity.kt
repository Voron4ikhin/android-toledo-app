package toledo24.pro.presentation.autorization

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.ActivityAutorizationBinding
import toledo24.pro.presentation.welcome.WelcomeViewModel

class AutorizationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAutorizationBinding
    private val vm by viewModel<AutorizationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetCode.setOnClickListener{
            val phone:String = binding.phoneInput.text.toString()
            val intent = Intent(this, AutorizationCode::class.java)
            val validPhone = vm.validationUserPhone(phone)
            if(validPhone){
                lifecycleScope.launchWhenStarted {
                    vm.getAutorizationCode(phone)
                    vm.checkStatePhone.onEach { value ->
                        if(value == "success") {
                            intent.putExtra("PHONE",phone)
                            startActivity(intent)
                        }
                    }.collect()
                }
            }
            else Toast.makeText(application, "Неверный номер телефона", Toast.LENGTH_SHORT).show()

        }

    }

}