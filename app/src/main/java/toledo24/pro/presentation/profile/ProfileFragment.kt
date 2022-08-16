package toledo24.pro.presentation.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.presentation.FragmentName
import toledo24.pro.presentation.MainActivity
import toledo24.pro.presentation.cart.CardViewModel

class ProfileFragment : Fragment(), FragmentName {

    lateinit var listener: MainActivity
    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.fragmentName.observe(requireActivity()) { name ->
            (listener as FragmentName).getFragmentName(name)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            listener = context
        }
    }

    override fun getFragmentName(name: String) {
        TODO("Not yet implemented")
    }

}