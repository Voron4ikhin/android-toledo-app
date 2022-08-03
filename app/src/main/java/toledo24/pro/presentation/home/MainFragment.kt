package toledo24.pro.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.app.POJO.BannerItem
import toledo24.pro.app.POJO.BestProductItem
import toledo24.pro.app.POJO.UIModel
import toledo24.pro.databinding.FragmentMainBinding
import toledo24.pro.domain.adapters.HomeAdapter
import kotlin.reflect.typeOf

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    private val adapter = HomeAdapter()
    private val imgLinks: MutableList<String> = arrayListOf()
    private val imageIdList = arrayListOf<UIModel>(
        UIModel.BannerModel(
            BannerItem(R.drawable.welcome_logo)
        ),
        UIModel.BannerModel(
            BannerItem(R.drawable.welcome_logo)
        ),
        UIModel.BannerModel(
            BannerItem(R.drawable.welcome_logo)
        ),
        UIModel.BannerModel(
            BannerItem(R.drawable.welcome_logo_400)
        ),
        UIModel.BestProductModel(
            BestProductItem("description 1")
        ),
        UIModel.BestProductModel(
            BestProductItem("description 2")
        ),
        UIModel.BestProductModel(
            BestProductItem("description 3")
        ),
        UIModel.BestProductModel(
            BestProductItem("description 4")
        ),

    )

    private val viewModel by viewModel<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)
        lifecycleScope.launchWhenStarted {
            viewModel.bannersList.collect {
                it.forEach { value ->
                    imgLinks.add(value.PICTURE)
                }
            }
        }
        init()
        return binding.root
    }

    private fun init() {
        binding.apply {
            rcView.adapter = adapter
        }

        adapter.submitData(imageIdList)

    }


    }
