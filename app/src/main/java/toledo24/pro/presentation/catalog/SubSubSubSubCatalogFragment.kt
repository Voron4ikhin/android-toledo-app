package toledo24.pro.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.FragmentCatalogBinding
import toledo24.pro.databinding.FragmentSubSubSubSubCatalogBinding
import toledo24.pro.databinding.FragmentSubcatalogBinding
import toledo24.pro.domain.adapters.CatalogAdapter

class SubSubSubSubCatalogFragment: Fragment() {

    private lateinit var binding: FragmentSubSubSubSubCatalogBinding
    private val viewModel by viewModel<CatalogFragmentViewModel>()
    private val adapter by lazy { CatalogAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSubSubSubSubCatalogBinding.inflate(inflater)
        binding.menuSubSubSubSubCatalogList.layoutManager = GridLayoutManager(context, 1)
        binding.menuSubSubSubSubCatalogList.adapter = adapter
        Log.d("tag", "Перешли во пятая фрагмент")

        val ID = requireArguments().getString("ID")
        val NAME = requireArguments().getString("NAME")
        val MAIN = requireArguments().getString("MAIN")

        binding.prevMenu.text = NAME
        binding.mainMenu.text = MAIN

        lifecycleScope.launchWhenStarted {
            if (ID != null) {
                viewModel.getRoomItemsList(ID)
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
                    Log.d("tag", "Конечная")
                }
            })

        binding.mainMenu.setOnClickListener{
            findNavController().navigate(R.id.action_subSubSubSubCatalogFragment_to_item_catalog)
        }


        return binding.root
    }

}