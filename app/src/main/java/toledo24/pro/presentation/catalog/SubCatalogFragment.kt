package toledo24.pro.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.FragmentSubcatalogBinding
import toledo24.pro.domain.adapters.CatalogAdapter


class SubCatalogFragment: Fragment() {

    private lateinit var binding: FragmentSubcatalogBinding
    private val viewModel by viewModel<CatalogFragmentViewModel>()
    private val adapter by lazy { CatalogAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSubcatalogBinding.inflate(inflater)
        binding.menuSubCatalogList.layoutManager = GridLayoutManager(context, 1)
        binding.menuSubCatalogList.adapter = adapter

        val ID = requireArguments().getString("ID")
        val NAME = requireArguments().getString("NAME")

        binding.prevMenu.text = NAME

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
                    bundle.putString("MAIN", NAME)
                    bundle.putString("CODE", adapter.getItem(position).CODE)
                    lifecycleScope.launchWhenStarted {
                        viewModel.getCountCategoriesList(adapter.getItem(position).PRODUCT_ID)
                        viewModel.countCategories.collect {
                            if (it == 0){
                                findNavController().navigate(R.id.catalogProductsFragment, bundle)
                            }
                            else{
                                findNavController().navigate(R.id.subSubCatalogFragment, bundle)
                            }
                        }
                    }

                }
            })

//        binding.prevMenu.setOnClickListener{
//            findNavController().navigate(R.id.item_catalog)
//        }

        binding.prevMenu.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("ID", ID)
            bundle.putString("NAME", NAME)
            val fragment: Fragment = CatalogFragment()
            fragment.arguments = bundle
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.item_catalog, fragment)
            //fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }




        return binding.root
    }

}