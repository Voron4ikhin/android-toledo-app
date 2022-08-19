package toledo24.pro.domain.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.databinding.ItemCardBinding
import toledo24.pro.presentation.cart.CardViewModel
import toledo24.pro.presentation.cart.CartFragment


class CardAdapterInStock(val сont: Context, viewModel: CardViewModel): RecyclerView.Adapter<CardAdapterInStock.CardHolder>(){

    private var cardList = ArrayList<BasketModel>()
    private lateinit var listener2: CartFragment
    private var clickListener: ClickListener? = null
    //private val viewModel by viewModel<CardViewModel>()
    private var viewModel: CardViewModel = viewModel


    inner class CardHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        val binding = ItemCardBinding.bind(item)
        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }

        fun bind(basketModel: BasketModel) = with(binding) {
            productName.text = basketModel.NAME
            cartPrice.text = basketModel.PRICE
            Picasso.get()
                .load(basketModel.PREVIEW_PICTURE)
                .placeholder(R.drawable.welcome_logo)
                .error(R.drawable.welcome_logo)
                .into(binding.imageCart)

            productCode.text = basketModel.CODE_PRODUCT
            productCount.text = basketModel.QUANTITY_INSTOCK.toString()
            if(basketModel.QUANTITY.toInt() > basketModel.RATE.toInt()) {
                minusButtonActive.visibility = View.VISIBLE
                minusButton.visibility = View.GONE
            }

            if(basketModel.QUANTITY.toInt() > basketModel.AMOUNT){
                plusButton.visibility = View.VISIBLE
                plusButtonActive.visibility = View.GONE
            }
            else{
                plusButton.visibility = View.GONE
                plusButtonActive.visibility = View.VISIBLE
            }

            plusButtonActive.setOnClickListener {
                viewModel.addToBasket(basketModel.PRODUCT_ID, basketModel.RATE)
                val counter = (productCount.text as String).toInt() + basketModel.RATE.toInt()
                if(counter > basketModel.RATE.toInt()) {
                    minusButton.visibility = View.GONE
                    minusButtonActive.visibility = View.VISIBLE
                }
                if(counter > basketModel.AMOUNT){
                    plusButton.visibility = View.VISIBLE
                    plusButtonActive.visibility = View.GONE
                    basketModel.QUANTITY_UNDER_ORDER += basketModel.RATE.toInt()
                }
                else{
                    productCount.text = counter.toString()
                }

            }

            minusButtonActive.setOnClickListener {
                viewModel.subtractFromBasket(basketModel.PRODUCT_ID, basketModel.RATE)
                if(basketModel.QUANTITY_UNDER_ORDER != 0){
                    basketModel.QUANTITY_UNDER_ORDER -= basketModel.RATE.toInt()
                    plusButton.visibility = View.GONE
                    plusButtonActive.visibility = View.VISIBLE
                }
                else{
                    val counter = (productCount.text as String).toInt() - basketModel.RATE.toInt()
                    if(counter == basketModel.RATE.toInt()) {
                        minusButton.visibility = View.VISIBLE
                        minusButtonActive.visibility = View.GONE
                    }
                    if(counter <= basketModel.AMOUNT){
                        plusButton.visibility = View.GONE
                        plusButtonActive.visibility = View.VISIBLE
                        productCount.text = counter.toString()
                    }
                }

            }

            menuCartItem.setOnClickListener { popupMenu(it, basketModel) }

        }

        private fun popupMenu(v: View, basketModel: BasketModel) {
            val popupMenus = PopupMenu(сont, v)
            popupMenus.inflate(R.menu.menu_item_cart)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.item_cart_repost ->{
                        Log.d("tag", "Репостим")
                        true
                    }
                    R.id.item_cart_favorite ->{
                        Log.d("tag", "Добавим в избранное")
                        true
                    }
                    R.id.item_cart_delete ->{
                        Log.d("tag", "Удаляем")
                        deleteFromCard(basketModel)
                        true
                    }

                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java).invoke(menu, true)


        }


        override fun onClick(view: View?) {
            if (view != null) {
                clickListener?.onItemClick(view,adapterPosition)
            }
        }


    }

    fun deleteFromCard(basketModel: BasketModel){
        var cardListNew = ArrayList<BasketModel>()
        cardList.forEach{
            if(it.CODE_PRODUCT != basketModel.CODE_PRODUCT){
                cardListNew.add(it)
            }
        }
        cardList = cardListNew
//        cardList.removeAll(1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardAdapterInStock.CardHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun getItem(position: Int): BasketModel{
        return cardList[position]
    }

    fun addToCard(basketModel: BasketModel) {
        cardList.add(basketModel)
        notifyDataSetChanged()
    }

    fun deleteAll(){
        cardList.clear()
    }

    fun setOnItemClickListener(clickListener: ClickListener) {

        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(v: View, position: Int)
    }



}