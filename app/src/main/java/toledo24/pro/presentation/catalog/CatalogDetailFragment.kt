package toledo24.pro.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.FragmentCatalogDetailBinding

class CatalogDetailFragment : Fragment() {

    private lateinit var binding: FragmentCatalogDetailBinding
    private val viewModel by viewModel<CatalogDetailFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentCatalogDetailBinding.inflate(inflater)
        //binding.rcView.layoutManager = GridLayoutManager(context, 2)

        val NAME = requireArguments().getString("NAME")
        val CATEGORY = requireArguments().getString("CATEGORY")

        // перебираем все потоки FLOW из collector и
        // проверяем нет ли там новых данных о категориях
        lifecycleScope.launchWhenStarted {
            if (CATEGORY != null && NAME != null ) {
                viewModel.getDetailProduct(CATEGORY, NAME)
                viewModel.detailProduct.collect {
                    binding.productName.text = it.NAME
                    Picasso.get().load(it.IMAGE).into(binding.productImage);
                }
            }
        }

        return binding.root
    }

}