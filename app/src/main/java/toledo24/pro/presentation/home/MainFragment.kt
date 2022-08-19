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
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)
        //binding.bannerImageSlider.layoutManager = GridLayoutManager(context, 1)
        binding.bannerImageSlider.setSliderAdapter(bannerAdapter)
//        binding.bannerImageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
//        binding.bannerImageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH;
//        binding.bannerImageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
//        binding.bannerImageSlider.scrollTimeInSec = 4; //set scroll delay in seconds :
        binding.bannerImageSlider.startAutoCycle()

//        viewModel.fragmentName.observe(requireActivity()) { name ->
//            (listener as FragmentName).getFragmentName(name)
//        }

        lifecycleScope.launchWhenStarted {
            viewModel.bannersList.collect {
                it.forEach { value ->
                    bannerAdapter.addBanner(value)
                }
            }
        }

//        lifecycleScope.launchWhenStarted {
//            viewModel.bannersList.collect {
//                it.forEach { value ->
//                    imgLinks.add(value.PICTURE)
//                }
//            }
//        }


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
