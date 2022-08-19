package toledo24.pro.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.mainPage.PopularProductsModel
import toledo24.pro.databinding.PopularItemBinding

class PopularProductAdapter: RecyclerView.Adapter<PopularProductAdapter.PopularProductHolder>()  {

    private val popularProductList = ArrayList<PopularProductsModel>()

    inner class PopularProductHolder(item: View) : RecyclerView.ViewHolder(item){

        val binding = PopularItemBinding.bind(item)


        fun bind(popularProductsModel: PopularProductsModel) = with(binding) {
            description.text = popularProductsModel.NAME
            Picasso.get()
                .load(popularProductsModel.DETAIL_PICTURE)
                .placeholder(R.drawable.welcome_logo)
                .error(R.drawable.welcome_logo)
                .into(binding.imagePreview)
            if(popularProductsModel.SALE !== null){
                cardPrice.text = popularProductsModel.SALE
                oldPrice.text = popularProductsModel.PRICE
            }
            else{
                cardPrice.text = popularProductsModel.PRICE
            }

            codeProduct.text = "Код: " +  popularProductsModel.CODE_PRODUCT

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularProductHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent, false)
        return PopularProductHolder(view)
    }

    override fun onBindViewHolder(holder: PopularProductHolder, position: Int) {
        holder.bind(popularProductList[position])
    }

    override fun getItemCount(): Int {
        return popularProductList.size
    }

    fun addProduct(popularProductsModel: PopularProductsModel){
        popularProductList.add(popularProductsModel)
        notifyDataSetChanged()
    }

}