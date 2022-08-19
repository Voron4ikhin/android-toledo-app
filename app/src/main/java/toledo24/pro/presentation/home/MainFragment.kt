package toledo24.pro.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.FragmentMainBinding
import toledo24.pro.domain.adapters.*
import toledo24.pro.presentation.MainActivity

class MainFragment : Fragment(){

    lateinit var listener: MainActivity
    lateinit var binding: FragmentMainBinding

    private val adapter = HomeAdapter()
    private val imgLinks: MutableList<String> = arrayListOf()

    private val viewModel by viewModel<NavigationViewModel>()
    private lateinit var sliderView : SliderView


    private val bannerAdapter by lazy { BannerAdapter() }
    private val popularProductAdapter by lazy { PopularProductAdapter() }
    private val brandAdapter by lazy { BrandAdapter() }


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

        binding.brand.adapter = brandAdapter
        binding.brand.layoutManager = GridLayoutManager(context, 4, LinearLayoutManager.HORIZONTAL, false)

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

        lifecycleScope.launch {
            viewModel.brandList.collect {
                it.forEach { value ->
                    brandAdapter.addBrand(value)
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
