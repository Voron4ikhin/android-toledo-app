package toledo24.pro.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.basket.BasketModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.databinding.ItemCardBinding

class CardAdapter: RecyclerView.Adapter<CardAdapter.CardHolder>() {

    private val cardList = ArrayList<BasketModel>()
    private var clickListener: ClickListener? = null


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
            productCount.text = basketModel.QUANTITY
            if(basketModel.QUANTITY.toInt() > basketModel.RATE.toInt()) {
                minusButtonActive.visibility = View.VISIBLE
                minusButton.visibility = View.GONE
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

    override fun onBindViewHolder(holder: CardAdapter.CardHolder, position: Int) {
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