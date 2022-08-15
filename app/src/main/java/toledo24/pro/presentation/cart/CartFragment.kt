package toledo24.pro.presentation.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.FragmentCartBinding
import toledo24.pro.databinding.FragmentCatalogBinding
import toledo24.pro.databinding.ItemCardBinding
import toledo24.pro.domain.adapters.CardAdapter
import toledo24.pro.domain.adapters.CatalogAdapter
import toledo24.pro.presentation.catalog.CatalogFragmentViewModel

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
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
        binding = FragmentCartBinding.inflate(inflater)
        binding.menuCardList.layoutManager = GridLayoutManager(context, 1)
        binding.menuCardList.adapter = adapterInStock
        //binding.menuCardListUnderOrder.adapter = adapterUnderOrder
        lifecycleScope.launch {
            Log.d("tag", "аходим2")
            viewModel.cardList.collect {
                it.forEach { value ->
                    adapterInStock.addToCard(value)
                }
            }


        }


//        lifecycleScope.launch {
//            Log.d("tag", "аходим")
//            viewModel.cardListInOrder.collect {
//                it.forEach { value ->
//                    adapterUnderOrder.addToCard(value)
//                }
//            }
//        }


        return binding.root
    }

}