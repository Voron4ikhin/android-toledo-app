package toledo24.pro.domain.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.databinding.ItemCardBinding
import toledo24.pro.presentation.cart.CardViewModel
import toledo24.pro.presentation.cart.CartFragment

class CardAdapterInStock(viewModel: CardViewModel): RecyclerView.Adapter<CardAdapterInStock.CardHolder>(){

    private val cardList = ArrayList<BasketModel>()
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
                Log.d("tag", "${basketModel.AMOUNT}")
                if(counter > 1) {
                    minusButton.visibility = View.GONE
                    minusButtonActive.visibility = View.VISIBLE
                }
                if(counter > basketModel.AMOUNT){
                    plusButton.visibility = View.VISIBLE
                    plusButtonActive.visibility = View.GONE
                    basketModel.QUANTITY_UNDER_ORDER += basketModel.RATE.toInt()
                    Log.d("tag", "Все в наличии закончено")
                }
                else{
                    productCount.text = counter.toString()
                }

            }

            minusButtonActive.setOnClickListener {
                viewModel.subtractFromBasket(basketModel.PRODUCT_ID, basketModel.RATE)
                Log.d("tag", "QUANTITY_UNDER_ORDER - ${basketModel.QUANTITY_UNDER_ORDER}")
                if(basketModel.QUANTITY_UNDER_ORDER != 0){
                    basketModel.QUANTITY_UNDER_ORDER -= basketModel.RATE.toInt()
                    plusButton.visibility = View.GONE
                    plusButtonActive.visibility = View.VISIBLE
                    Log.d("tag", "в if зашли")
                }
                else{
                    Log.d("tag", "в else зашли")
                    val counter = (productCount.text as String).toInt() - basketModel.RATE.toInt()
                    if(counter == 1) {
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

        }


        override fun onClick(view: View?) {
            if (view != null) {
                clickListener?.onItemClick(view,adapterPosition)
            }
        }


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