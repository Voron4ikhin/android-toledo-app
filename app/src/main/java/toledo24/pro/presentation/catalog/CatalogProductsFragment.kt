package toledo24.pro.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.FragmentCatalogProductsBinding
import toledo24.pro.databinding.ItemWithTextBinding
import toledo24.pro.domain.adapters.CatalogProductAdapter


class CatalogProductsFragment : Fragment() {

    private lateinit var binding: FragmentCatalogProductsBinding
    private val viewModel by viewModel<CatalogProductsViewModel>()
    //адаптер для RV
    private val adapter by lazy { CatalogProductAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentCatalogProductsBinding.inflate(inflater)
        binding.productList.layoutManager = GridLayoutManager(context, 2)
        binding.productList.adapter = adapter

        val code = requireArguments().getString("CODE")


        lifecycleScope.launchWhenStarted {
            if (code != null) {
                viewModel.getCatalogList(code, "1")
                viewModel.catalogProduct.collect {value ->
                    value.forEach {
                        Log.d("tag", "${it.NAME}")
                        adapter.addCatalog(it)
                    }
                }
            }

        }

        adapter.setOnItemClickListener(
            object : CatalogProductAdapter.ClickListener {
                override fun onItemClick(v: View, position: Int) {
                    Log.d("tag", "Мы нажали на что-то ${adapter.getItem(position).NAME}")

                }
            })

        return binding.root
    }


}

