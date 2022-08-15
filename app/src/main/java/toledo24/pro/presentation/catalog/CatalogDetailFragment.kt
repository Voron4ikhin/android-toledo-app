package toledo24.pro.presentation.catalog

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.FragmentCatalogDetailBinding
import toledo24.pro.domain.adapters.AnalogRelatedAdapter
import toledo24.pro.domain.adapters.CatalogAdapter
import toledo24.pro.domain.adapters.PropertiesAdapter

class CatalogDetailFragment : Fragment() {

    private lateinit var binding: FragmentCatalogDetailBinding
    private val viewModel by viewModel<CatalogDetailFragmentViewModel>()
    private val adapter by lazy { PropertiesAdapter() }
    private val adapterAnalogRelated by lazy { AnalogRelatedAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentCatalogDetailBinding.inflate(inflater)
        ViewCompat.setTransitionName(binding.productImage, "detailImage")
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)

        val NAME = requireArguments().getString("NAME")
        val CATEGORY = requireArguments().getString("CATEGORY")

        // перебираем все потоки FLOW из collector и
        // проверяем нет ли там новых данных о категориях
        lifecycleScope.launchWhenStarted {
            if (CATEGORY != null && NAME != null ) {
                viewModel.getDetailProduct(CATEGORY, NAME)
                viewModel.detailProduct.collect {value ->
                    binding.productName.text = value.NAME
                    if(value.SALE !== null){
                        binding.productPriceNew.text = value.SALE
                        binding.productPriceOld.text = value.PRICE
                    }
                    else{
                        binding.productPriceNew.text = value.PRICE
                    }

                    binding.descriptionDetail.text = value.PREVIEW_TEXT
                    binding.codeProductDetail.text = "Код товара: " + value.CODE_PRODUCT
                    binding.productPriceNew
                    Picasso.get().load(value.IMAGE).into(binding.productImage);

                    binding.moreDescription.setOnClickListener {
                        binding.moreDescription.visibility = View.INVISIBLE
                        binding.descriptionDetail.text = value.DETAIL_TEXT
                    }

                    binding.descriptionDetail.setOnClickListener {
                        binding.moreDescription.visibility = View.INVISIBLE
                        binding.descriptionDetail.text = value.DETAIL_TEXT
                    }

                    binding.propertiesDetail.adapter = adapter
                    value.PROPERTIES.forEach {
                        adapter.addProperty(it.value)
                    }

                    var relatedXml : String = ""
                    value.RELATED.forEach {
                        relatedXml += "$it,"
                    }

                    //Аналоги и Сопутка
                    binding.analogRV.adapter = adapterAnalogRelated
                    binding.analogRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    viewModel.getAnalogsRelated(relatedXml)         //Получаем Аналоги и Сопутку
                    viewModel.relatedProduct.collect { value ->
                        value.forEach {
                            adapterAnalogRelated.addRelated(it)
                        }
                    }




                }
            }
        }


        return binding.root
    }

}