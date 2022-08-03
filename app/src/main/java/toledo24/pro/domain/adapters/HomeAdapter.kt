package toledo24.pro.domain.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import toledo24.pro.R
import toledo24.pro.app.POJO.UIModel
import toledo24.pro.databinding.BannerItemBinding
import toledo24.pro.databinding.BestProductItemBinding

class HomeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // val bannerList = ArrayList<BannerItem>()
    private var arrayList: ArrayList<UIModel> = ArrayList()

    fun submitData(list: ArrayList<UIModel>) {
        arrayList.clear()
        arrayList.addAll(list)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int) = when (arrayList[position]) {
        is UIModel.BannerModel -> R.layout.banner_item
        is UIModel.BestProductModel -> R.layout.best_product_item
        null -> throw IllegalStateException("Unknown view")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.banner_item -> BannerViewHolder(view)
            R.layout.best_product_item -> BestProductViewHolder(view)
            else ->LoadingViewholder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = arrayList[position]
        when (holder) {
                is BannerViewHolder -> holder.bind(item as UIModel.BannerModel)
                is BestProductViewHolder -> holder.bind(item as UIModel.BestProductModel)
        }

    }

    class BannerViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = BannerItemBinding.bind(item)
        fun bind(banner: UIModel.BannerModel) = with(binding) {
            homeImageView.setImageResource(banner.bannerItem.imageId)
        }
    }

    class BestProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = BestProductItemBinding.bind(item)
        fun bind(bestProductItem: UIModel.BestProductModel) = with(binding) {
            tvTitle.text = bestProductItem.BestProductItem.description
        }
    }

    class LoadingViewholder(item: View) : RecyclerView.ViewHolder(item) {

    }


}