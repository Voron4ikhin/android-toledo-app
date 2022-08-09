package toledo24.pro.domain.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.databinding.ItemWithTextBinding
import toledo24.pro.presentation.catalog.CatalogProductsViewModel


class CatalogProductAdapter(viewModel: CatalogProductsViewModel) : RecyclerView.Adapter<CatalogProductAdapter.CatalogProductHolder>(){

    private val catalogList = ArrayList<CatalogItemModel>()
    private lateinit var clickListener: OnItemClickListener
    private var vm: CatalogProductsViewModel = viewModel

    inner class CatalogProductHolder(itemView: View, listener : OnItemClickListener) : RecyclerView.ViewHolder(itemView){

        private val binding = ItemWithTextBinding.bind(itemView)

        fun bind(catalogModel: CatalogItemModel) = with(binding) {
            description.text = catalogModel.NAME
            Picasso.get()
                .load(catalogModel.DETAIL_PICTURE)
                .placeholder(R.drawable.welcome_logo)
                .error(R.drawable.welcome_logo)
                .into(binding.imagePreview)
            if(catalogModel.SALE !== null){
                cardPrice.text = catalogModel.SALE
                oldPrice.text = catalogModel.PRICE
            }
            else{
                cardPrice.text = catalogModel.PRICE
            }

            codeProduct.text = "Код: " +  catalogModel.CODE_PRODUCT


            cardClick.setOnClickListener {
                Log.d("tag", "Нажали на корзину ${catalogModel.CODE} + ${catalogModel.CATEGORY_NAME}")
                vm.addToBasket(catalogModel.PRODUCT_ID, catalogModel.RATE)
            }
        }

    }




    fun  fireItemClicked(itemView: View, position: Int, item: CatalogItemModel){

        if (clickListener != null) {
            clickListener.onItemClick(position, item);
        }

    }

    /**
     * Создается элемент списка
     * надувает view и загружает его в память
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_with_text, parent, false)
        val holder = CatalogProductHolder(view, clickListener)

        holder.itemView.setOnClickListener { v: View ->
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                fireItemClicked(v, position, catalogList[position])
            }
        }

        return holder
    }

    /**
     * Заполняется элемент списка
     */
    override fun onBindViewHolder(holder: CatalogProductHolder, position: Int) {
        holder.bind(catalogList[position])
    }

    override fun getItemCount(): Int {
        return catalogList.size
    }

    fun getItem(position: Int): CatalogItemModel {
        return catalogList[position]
    }

    fun addCatalog(catalogItemModel: CatalogItemModel) {
        catalogList.add(catalogItemModel)
        notifyDataSetChanged()
    }

    fun deleteAll(){
        catalogList.clear()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int, item : CatalogItemModel)
    }



}