package toledo24.pro.presentation.catalog

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


class CatalogProductsFragment : Fragment() {

    private lateinit var binding: FragmentCatalogProductsBinding
    private val viewModel by viewModel<CatalogProductsViewModel>()
    //адаптер для RV
    private val adapter by lazy { CatalogProductAdapter(viewModel) }

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
                viewModel.getCatalogList(category, "1")
                viewModel.catalogProduct.collect {value ->
                    value.forEach {
                        adapter.addCatalog(it)
                    }
                }
            }


//            viewModel.basket.collect{value ->
//                value.forEach{
//                    Log.d("tag", "BIG KEKW: ${it.key} - ${it.value.NAME}")
//                }
//            }

        }


        viewModel.toast.observe(requireActivity()) { elem ->
            if (elem) Toast.makeText(activity, "Товар добавлен в корзину!", Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(activity, "Товар добавлен в корзину!", Toast.LENGTH_SHORT).show()

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


}

