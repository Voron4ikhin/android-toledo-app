package toledo24.pro.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.databinding.FragmentCatalogBinding
import toledo24.pro.domain.adapters.CatalogAdapter


class CatalogFragment : Fragment() {

    private lateinit var binding: FragmentCatalogBinding
    private val viewModel by viewModel<CatalogFragmentViewModel>()
    //адаптер для RV
    private val adapter by lazy { CatalogAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentCatalogBinding.inflate(inflater)
        binding.menuCatalogList.layoutManager = GridLayoutManager(context, 1)
        binding.menuCatalogList.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.categoriesList.collect {
                it.forEach { value ->
                    adapter.addCatalog(value)
                }
            }
        }

        adapter.setOnItemClickListener(
            object : CatalogAdapter.ClickListener {
                override fun onItemClick(v: View, position: Int) {
                    val bundle = Bundle()
                    bundle.putString("ID", adapter.getItem(position).PRODUCT_ID)
                    bundle.putString("NAME", adapter.getItem(position).NAME)
                    findNavController().navigate(R.id.action_item_catalog_to_item_subcatalog, bundle)

                }
            })

        return binding.root
    }


}

