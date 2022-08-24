package toledo24.pro.presentation.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.FragmentProfileBinding
import toledo24.pro.presentation.FragmentName
import toledo24.pro.presentation.MainActivity
import toledo24.pro.presentation.cart.CardViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<ProfileViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(inflater)

        lifecycleScope.launchWhenStarted {
            viewModel.favoriteCount.collect {
                //кол-во избранных товаров
            }
        }
        return binding.root
    }
}