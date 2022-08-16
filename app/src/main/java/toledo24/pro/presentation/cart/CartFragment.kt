package toledo24.pro.presentation.cart

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.databinding.FragmentCartBinding
import toledo24.pro.domain.adapters.CardTabAdapter
import toledo24.pro.presentation.MainActivity


const val ARG_OBJECT = "object"

class CartFragment : Fragment() {

    lateinit var listener: MainActivity
    private lateinit var binding: FragmentCartBinding
    private val viewModel by viewModel<CardViewModel>()
    //адаптер для RV
    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private lateinit var InStock: CartInStockFragment
    private lateinit var UnderOrder: CartUnderOrderFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater)

        lifecycleScope.launchWhenStarted {
            viewModel.cardListInOrder.collect { value ->
                value.forEach {
                    Log.d("tag", "${it.QUANTITY_INSTOCK}")
                }

            }
        }


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



        //Ставим badge на табы
        val badgeDrawable = tabLayout.getTabAt(0)!!.orCreateBadge
        badgeDrawable.isVisible = true
        badgeDrawable.number = 5



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

//
//    override fun getFragmentName(name: String) {
//        TODO("Not yet implemented")
//    }

}