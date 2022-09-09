package toledo24.pro.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.FragmentProfileInfoBinding

class ProfileDetailFragment : Fragment() {

    private lateinit var binding: FragmentProfileInfoBinding
    private val viewModel by viewModel<ProfileInfoFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileInfoBinding.inflate(inflater)

        lifecycleScope.launchWhenStarted {
            viewModel
        }

        binding.exitProfile.setOnClickListener{
            viewModel.userInfo.value?.let { it1 -> viewModel.exit(it1) }
            findNavController().navigate(R.id.action_profileDetailFragment_to_autorizationActivity);
        }


        return binding.root



    }



}