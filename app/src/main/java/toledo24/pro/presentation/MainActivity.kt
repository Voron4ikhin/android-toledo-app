package toledo24.pro.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import toledo24.pro.R
import toledo24.pro.databinding.ActivityMainBinding
import toledo24.pro.presentation.home.NavigationViewModel

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
        getSupportActionBar()?.title = null

        viewModel.updateBadgeCount()        // Обновляем данные через Api и записываем в Room
        listenCountBasket()         // Слушаем LiveData размер корзины
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        getMenuInflater().inflate(R.menu.main_menu, menu)
        searchView.queryHint = "введите название товара"
        return true
    }

    override fun updateBasketBadge(number: Int) {
        if (number == 0) binding.navView.removeBadge(R.id.item_cart)          //удаляем значок если корзина пустая
        else {
            binding.navView.getOrCreateBadge(R.id.item_cart).number = number //получаем экземпляр класса badge (значок над корзиной)
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

}