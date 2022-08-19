package toledo24.pro.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import toledo24.pro.R
import toledo24.pro.data.network.mainPage.BannerListModel
import toledo24.pro.databinding.BannerItemBinding

class BannerAdapter : SliderViewAdapter<BannerAdapter.Holder>() {

    private var bannerList = ArrayList<BannerListModel>()

    inner class Holder(item: View) : SliderViewAdapter.ViewHolder(item) {

        val binding = BannerItemBinding.bind(item)

        fun bind(bannerListModel : BannerListModel) = with(binding){
            Picasso.get()
                .load(bannerListModel.PICTURE)
                .placeholder(R.drawable.welcome_logo)
                .error(R.drawable.welcome_logo)
                .into(binding.homeImageView)

        }

    }

    fun addBanner(bannerListModel :BannerListModel ){
        bannerList.add(bannerListModel)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return bannerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.bind(bannerList[position])
    }
}