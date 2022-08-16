package toledo24.pro.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.ActivityMainBinding
import toledo24.pro.domain.adapters.CardTabAdapter
import toledo24.pro.presentation.cart.CartFragment
import toledo24.pro.presentation.cart.CartUnderOrderFragment

class MainActivity: AppCompatActivity(), Badge{

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        supportActionBar?.title = null


        viewModel.updateBadgeCount()        // Обновляем данные через Api и записываем в Room
        listenCountBasket()         // Слушаем LiveData размер корзины


    }

    override fun updateBasketBadge(number: Int) {
        if (number == 0) binding.navView.removeBadge(R.id.item_cart)                 //удаляем значок если корзина пустая
        else {
            binding.navView.getOrCreateBadge(R.id.item_cart).number = number        //получаем экземпляр класса badge (значок над корзиной)
        }

    }



    fun listenCountBasket(){
        viewModel.badgeCount.observe(this){number ->
            if (number == 0) binding.navView.removeBadge(R.id.item_cart)          //удаляем значок если корзина пустая
            else {
                binding.navView.getOrCreateBadge(R.id.item_cart).number = number //получаем экземпляр класса badge (значок над корзиной)
            }
        }
    }

//    override fun getFragmentName(name: String) {
//        TODO("Not yet implemented")
//    }


//    override fun getFragmentName(name: String) {
//        //val fragment = findViewById(R.id.nav_host_fragment_activity_main)
//        if (name ==  "card"){
//            binding.tabLayout.visibility = View.VISIBLE
//            binding.viewPager.visibility = View.VISIBLE
//
//        }
//        else{
//            binding.tabLayout.visibility = View.GONE
//            binding.viewPager.visibility = View.GONE
//        }
//    }


}