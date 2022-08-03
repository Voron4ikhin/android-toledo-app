package toledo24.pro.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.FragmentSubSubCatalogBinding
import toledo24.pro.domain.adapters.CatalogAdapter

class SubSubCatalogFragment: Fragment() {

    private lateinit var binding: FragmentSubSubCatalogBinding
    private val viewModel by viewModel<CatalogFragmentViewModel>()
    private val adapter by lazy { CatalogAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSubSubCatalogBinding.inflate(inflater)
        binding.menuSubSubCatalogList.layoutManager = GridLayoutManager(context, 1)
        binding.menuSubSubCatalogList.adapter = adapter

        val ID = requireArguments().getString("ID")
        val NAME = requireArguments().getString("NAME")
        val MAIN = requireArguments().getString("MAIN")

        binding.prevMenu.text = NAME
        binding.mainMenu.text = MAIN

        lifecycleScope.launchWhenStarted {
            if (ID != null) {
                viewModel.getRoomItemsList(ID)
                Log.d("tag", "Получили ID ${ID}")
            }
            viewModel.categoriesList.collect {
                adapter.deleteAll()
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
                    bundle.putString("MAIN", MAIN)
                    bundle.putString("CODE", adapter.getItem(position).CODE)
                    lifecycleScope.launchWhenStarted {
                        viewModel.getCountCategoriesList(adapter.getItem(position).PRODUCT_ID)
                        viewModel.countCategories.collect {
                            if (it == 0){
                                findNavController().navigate(R.id.catalogProductsFragment, bundle)
                            }
                            else{
                                findNavController().navigate(R.id.subSubSubCatalogFragment, bundle)
                            }
                        }
                    }

                }
            })

        binding.prevMenu.setOnClickListener{
            
        }

//        binding.mainMenu.setOnClickListener{
//            findNavController().navigate(R.id.action_subSubCatalogFragment_to_item_catalog)
//        }



        return binding.root
    }

}