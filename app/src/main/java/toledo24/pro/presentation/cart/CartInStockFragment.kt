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
import toledo24.pro.databinding.FragmentCartBinding
import toledo24.pro.databinding.FragmentCartInStockBinding
import toledo24.pro.domain.adapters.CardAdapter
import toledo24.pro.presentation.FragmentName
import toledo24.pro.presentation.MainActivity

class CartInStockFragment: Fragment() {

    lateinit var listener: MainActivity
    private lateinit var binding:FragmentCartInStockBinding
    private val viewModel by viewModel<CardViewModel>()
    //адаптер для RV
    private val adapterInStock by lazy { CardAdapter() }
    private val adapterUnderOrder by lazy { CardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = FragmentCartInStockBinding.inflate(inflater)
        binding.menuCardList.layoutManager = GridLayoutManager(context, 1)
        binding.menuCardList.adapter = adapterInStock

        lifecycleScope.launch {
            viewModel.cardList.collect {
                it.forEach { value ->
                    adapterInStock.addToCard(value)
                }
            }
        }

//        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
//            val textView :TextView = binding.StockTV
//            textView.text = getInt(ARG_OBJECT).toString()
//        }

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


//    override fun getFragmentName(name: String) {
//        TODO("Not yet implemented")
//    }

}