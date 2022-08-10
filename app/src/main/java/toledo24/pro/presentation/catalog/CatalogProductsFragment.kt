package toledo24.pro.presentation.catalog

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.databinding.FragmentCatalogProductsBinding
import toledo24.pro.databinding.ItemWithTextBinding
import toledo24.pro.domain.adapters.CatalogProductAdapter
import toledo24.pro.presentation.Badge
import toledo24.pro.presentation.MainActivity
import toledo24.pro.presentation.MainViewModel


class CatalogProductsFragment : Fragment(), Badge {

    lateinit var listener: MainActivity
    private lateinit var binding: FragmentCatalogProductsBinding
    private val viewModel by viewModel<CatalogProductsViewModel>()
    private val adapter by lazy { CatalogProductAdapter(viewModel) }  //адаптер для RV

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentCatalogProductsBinding.inflate(inflater)
        binding.productList.layoutManager = GridLayoutManager(context, 2)
        binding.productList.adapter = adapter

        val category = requireArguments().getString("CODE")

        lifecycleScope.launchWhenStarted {
            if (category != null) {
                viewModel.getCatalogList(category, "1")         //Посмотреть страницу
                viewModel.catalogProduct.collect {value ->
                    value.forEach {
                        adapter.addCatalog(it)
                    }
                }
            }

        }

        //Слушаем LiveData toast и показываем Toast
        viewModel.toast.observe(requireActivity()) { elem ->
            //if (elem) Toast.makeText(activity, "Товар добавлен в корзину!", Toast.LENGTH_SHORT).show()
        }

        //Слушаем LiveData и показываем size в bottomNavigation
        viewModel.basketCount.observe(requireActivity()) { number ->
            (listener as Badge).updateBasketBadge(number)
        }

        adapter.setOnItemClickListener(
            object : CatalogProductAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, item: CatalogItemModel) {
                    val bundle = Bundle()
                    Log.d("tag", "${item.NAME}")
                    bundle.putString("NAME", adapter.getItem(position).CODE)
                    bundle.putString("CATEGORY", category)
                    findNavController().navigate(R.id.catalogDetailFragment, bundle)
                }

            })

        return binding.root
    }

    override fun updateBasketBadge(number: Int) {
        TODO("Not yet implemented")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            listener = context
        }
    }



}

