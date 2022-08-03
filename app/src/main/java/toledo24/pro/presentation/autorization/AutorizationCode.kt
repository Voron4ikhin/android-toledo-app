package toledo24.pro.presentation.autorization

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.ActivityAutorizationCodeBinding
import toledo24.pro.presentation.MainActivity

class AutorizationCode: AppCompatActivity()  {

    private lateinit var binding: ActivityAutorizationCodeBinding
    private val vm by viewModel<AutorizationCodeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutorizationCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val phone = intent.getStringExtra("PHONE").toString()

        binding.btnAutorization.setOnClickListener{
            val code:String = binding.codeInput.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            val validCode = vm.validationUserCode(code)
            if(validCode){
                lifecycleScope.launchWhenStarted {
                    vm.checkAutorizationCode(phone, code)
                    vm.checkStatusCode.onEach { value ->
                        if(value) {
                            startActivity(intent)
                        }
                    }.collect()
                }
            }
            else Toast.makeText(application, "Неверный номер телефона", Toast.LENGTH_SHORT).show()
        }
    }

}