package toledo24.pro.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.catalog.CatalogItemModel
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.databinding.ItemWithTextBinding

class CatalogProductAdapter : RecyclerView.Adapter<CatalogProductAdapter.CatalogHolder>(){

    private val catalogList = ArrayList<CatalogItemModel>()
    private var clickListener: ClickListener? = null

    // adapter (шаблон по которому будет происходить заполнение данных)
    inner class CatalogHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        val binding = ItemWithTextBinding.bind(item)

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }

        fun bind(catalogModel: CatalogItemModel) = with(binding) {
            description.text = catalogModel.NAME
            Picasso.get()
                .load(catalogModel.DETAIL_PICTURE)
                .placeholder(R.drawable.welcome_logo)
                .error(R.drawable.welcome_logo)
                .into(binding.imagePreview)
        }

        override fun onClick(view: View?) {
            if (view != null) {
                clickListener?.onItemClick(view,adapterPosition)
            }
        }

    }

    /**
     * Создается элемент списка
     * надувает view и загружает его в память
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_with_text, parent, false)
        return CatalogHolder(view)
    }

    /**
     * Заполняется элемент списка
     */
    override fun onBindViewHolder(holder: CatalogHolder, position: Int) {
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

    fun setOnItemClickListener(clickListener: CatalogProductAdapter.ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(v: View, position: Int)
    }


}