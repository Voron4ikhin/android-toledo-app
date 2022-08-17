package toledo24.pro.presentation.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.FragmentCartBinding
import toledo24.pro.domain.adapters.CardTabAdapter
import toledo24.pro.presentation.Badge
import toledo24.pro.presentation.MainActivity

class CartFragment : Fragment(), Badge {

    lateinit var listener: MainActivity
    private lateinit var binding: FragmentCartBinding
    private val viewModel by viewModel<CardViewModel>()
    //адаптер для RV
    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private lateinit var InStock: CartInStockFragment
    private lateinit var UnderOrder: CartUnderOrderFragment

    private var countInStock: Int = 0
    private var countUnderOrder: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater)

        toolbar = binding.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        InStock = CartInStockFragment()
        UnderOrder = CartUnderOrderFragment()

        tabLayout.setupWithViewPager(viewPager);

        val viewPagerAdapter = fragmentManager?.let { CardTabAdapter(it, 0) }

        viewPagerAdapter?.addFragment(InStock, "В наличии");
        viewPagerAdapter?.addFragment(UnderOrder, "Под заказ");
        viewPager.adapter = viewPagerAdapter;

        val badgeDrawableInStock = tabLayout.getTabAt(0)!!.orCreateBadge
        val badgeDrawableUnderOrder = tabLayout.getTabAt(1)!!.orCreateBadge
        badgeDrawableInStock.isVisible = false
        badgeDrawableUnderOrder.isVisible = false


        lifecycleScope.launchWhenStarted {
            viewModel.cardList.collect { value ->
                value.forEach {
                    countInStock += it.QUANTITY_INSTOCK
                    //Ставим badge на табы
                }
                if(countInStock != 0) {
                    badgeDrawableInStock.isVisible = true
                    badgeDrawableInStock.number = countInStock
                    (listener as Badge).updateBasketBadge(countUnderOrder + countInStock)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.cardListInOrder.collect { value ->
                value.forEach {
                    countUnderOrder += it.QUANTITY_UNDER_ORDER
                }
                if(countUnderOrder != 0){
                    badgeDrawableUnderOrder.isVisible = true
                    badgeDrawableUnderOrder.number = countUnderOrder
                    (listener as Badge).updateBasketBadge(countUnderOrder + countInStock)
                }
            }
        }


        //Слушаем LiveData и показываем size в bottomNavigation
//        viewModel.fragmentName.observe(requireActivity()) { name ->
//            (listener as FragmentName).getFragmentName(name)
//        }

        return binding.root
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            listener = context
        }
    }

    override fun updateBasketBadge(number: Int) {
        TODO("Not yet implemented")
    }



}