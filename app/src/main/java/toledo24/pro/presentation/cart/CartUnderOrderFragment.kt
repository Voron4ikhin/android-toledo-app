package toledo24.pro.presentation.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.FragmentCartUnderOrderBinding
import toledo24.pro.domain.adapters.CardAdapterUnderOrder
import toledo24.pro.presentation.MainActivity

class CartUnderOrderFragment: Fragment() {
    lateinit var listener: MainActivity
    private lateinit var binding: FragmentCartUnderOrderBinding
    private val viewModel by viewModel<CardViewModel>()
    //адаптер для RV
    private val adapterUnderOrder by lazy { CardAdapterUnderOrder() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = FragmentCartUnderOrderBinding.inflate(inflater)
        binding.menuCardListUnderOrder.layoutManager = GridLayoutManager(context, 1)
        binding.menuCardListUnderOrder.adapter = adapterUnderOrder

        lifecycleScope.launch {
            viewModel.cardListInOrder.collect {
                it.forEach { value ->
                    adapterUnderOrder.addToCard(value)
                }
            }
        }

        //Слушаем LiveData и показываем size в bottomNavigation
//        viewModel.fragmentName.observe(requireActivity()) { name ->
//            (listener as FragmentName).getFragmentName(name)
//        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            listener = context
        }
    }
}