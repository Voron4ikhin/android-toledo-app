package toledo24.pro.presentation.cart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.FragmentCartInStockBinding
import toledo24.pro.domain.adapters.CardAdapterInStock
import toledo24.pro.presentation.Badge
import toledo24.pro.presentation.MainActivity

class CartInStockFragment: Fragment(), Badge {

    lateinit var listener: MainActivity
    private lateinit var binding:FragmentCartInStockBinding
    private val viewModel by viewModel<CardViewModel>()
    //адаптер для RV
    private val adapterInStock by lazy { CardAdapterInStock(requireActivity(), viewModel) }

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
                viewModel.basketCount.observe(requireActivity()) { number ->
                    run {
                        (listener as Badge).updateBasketBadge(number)
                        Log.d("tag", "Уже меняем после +1")
                    }
                    //(listener as Badge).updateBasketBadge(number)
                }
            }

        }

//        lifecycleScope.launch {
//            viewModel.basketCount.observe(requireActivity()) { number ->
//                (listener as Badge).updateBasketBadge(number)
//            }
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

    override fun updateBasketBadge(number: Int) {
        TODO("Not yet implemented")
    }

}