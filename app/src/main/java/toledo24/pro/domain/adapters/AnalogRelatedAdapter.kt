package toledo24.pro.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.data.network.catalog.DetailProductModel
import toledo24.pro.databinding.ItemAnalogRelatedBinding
import toledo24.pro.databinding.ItemWithTextBinding

class AnalogRelatedAdapter : RecyclerView.Adapter<AnalogRelatedAdapter.AnalogRelatedHolder>()  {

    private val catalogList = ArrayList<DetailProductModel>()
    //private lateinit var clickListener: AnalogRelatedAdapter.OnItemClickListener

    // adapter (шаблон по которому будет происходить заполнение данных)
    inner class AnalogRelatedHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = ItemAnalogRelatedBinding.bind(item)

        fun bind(detailProductModel: DetailProductModel) = with(binding) {
            description.text = detailProductModel.NAME
            Picasso.get()
                .load(detailProductModel.DETAIL_PICTURE)
                .placeholder(R.drawable.welcome_logo)
                .error(R.drawable.welcome_logo)
                .into(binding.imagePreview)
            if(detailProductModel.SALE !== null){
                cardPrice.text = detailProductModel.SALE
                oldPrice.text = detailProductModel.PRICE
            }
            else{
                cardPrice.text = detailProductModel.PRICE
            }

            codeProduct.text = "Код: " +  detailProductModel.CODE_PRODUCT


        }


    }

//    fun  fireItemClicked(itemView: View, position: Int, item: DetailProductModel){
//
//        if (clickListener != null) {
//            clickListener.onItemClick(position, item);
//        }
//
//    }

    /**
     * Создается элемент списка
     * надувает view и загружает его в память
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalogRelatedAdapter.AnalogRelatedHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_analog_related, parent, false)
        val holder = AnalogRelatedHolder(view)

//        holder.itemView.setOnClickListener { v: View ->
//            val position = holder.adapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                fireItemClicked(v, position, catalogList[position])
//            }
//        }

        return holder
    }

    /**
     * Заполняется элемент списка
     */
    override fun onBindViewHolder(holder: AnalogRelatedAdapter.AnalogRelatedHolder, position: Int) {
        holder.bind(catalogList[position])
    }

    override fun getItemCount(): Int {
        return catalogList.size
    }

    fun getItem(position: Int): DetailProductModel {
        return catalogList[position]
    }

    fun addRelated(detailProductModel: DetailProductModel) {
        catalogList.add(detailProductModel)
        notifyDataSetChanged()
    }

    fun deleteAll(){
        catalogList.clear()
    }

//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        clickListener = listener
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(position: Int, item : DetailProductModel)
//    }

}