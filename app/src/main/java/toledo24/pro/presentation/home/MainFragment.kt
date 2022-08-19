package toledo24.pro.presentation.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.app.POJO.BannerItem
import toledo24.pro.app.POJO.BestProductItem
import toledo24.pro.app.POJO.UIModel
import toledo24.pro.databinding.FragmentCatalogBinding
import toledo24.pro.databinding.FragmentMainBinding
import toledo24.pro.domain.adapters.BannerAdapter
import toledo24.pro.domain.adapters.CatalogAdapter
import toledo24.pro.domain.adapters.HomeAdapter
import toledo24.pro.domain.adapters.PopularProductAdapter
import toledo24.pro.presentation.FragmentName
import toledo24.pro.presentation.MainActivity
import kotlin.reflect.typeOf

class MainFragment : Fragment(){

    lateinit var listener: MainActivity
    lateinit var binding: FragmentMainBinding

    private val adapter = HomeAdapter()
    private val imgLinks: MutableList<String> = arrayListOf()

    private val viewModel by viewModel<NavigationViewModel>()
    private lateinit var sliderView : SliderView


    private val bannerAdapter by lazy { BannerAdapter() }
    private val popularProductAdapter by lazy { PopularProductAdapter() }
    private val r by lazy {Adapter()}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)
        //binding.bannerImageSlider.layoutManager = GridLayoutManager(context, 1)
        binding.bannerImageSlider.setSliderAdapter(bannerAdapter)
        binding.bannerImageSlider.startAutoCycle()


        binding.popularProducts.adapter = popularProductAdapter
        binding.popularProducts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//        viewModel.fragmentName.observe(requireActivity()) { name ->
//            (listener as FragmentName).getFragmentName(name)
//        }

        lifecycleScope.launchWhenStarted {
            viewModel.popularList.collect {
                it.forEach { value ->
                    popularProductAdapter.addProduct(value)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.bannersList.collect {
                it.forEach { value ->
                    bannerAdapter.addBanner(value)
                }
            }
        }


        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            listener = context
        }
    }

//    override fun getFragmentName(name: String) {
//        TODO("Not yet implemented")
//    }


}
