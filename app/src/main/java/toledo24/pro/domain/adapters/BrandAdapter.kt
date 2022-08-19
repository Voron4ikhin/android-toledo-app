package toledo24.pro.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.mainPage.BrandsListModel
import toledo24.pro.databinding.BrandItemBinding

class BrandAdapter: RecyclerView.Adapter<BrandAdapter.BrandHolder>()  {

    private val brandList = ArrayList<BrandsListModel>()

    inner class BrandHolder(item: View) : RecyclerView.ViewHolder(item){

        val binding = BrandItemBinding.bind(item)

        fun bind(brandsListModel : BrandsListModel) = with(binding){
            Picasso.get()
                .load(brandsListModel.IMAGE)
                .placeholder(toledo24.pro.R.drawable.welcome_logo)
                .error(toledo24.pro.R.drawable.welcome_logo)
                .into(binding.brandImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item, parent, false)
        return BrandHolder(view)
    }

    override fun onBindViewHolder(holder: BrandHolder, position: Int) {
        holder.bind(brandList[position])
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    fun addBrand(brandsListModel : BrandsListModel){
        brandList.add(brandsListModel)
        notifyDataSetChanged()
    }
}