package toledo24.pro.domain.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import toledo24.pro.R
import toledo24.pro.data.network.catalog.PropertiesModel
import toledo24.pro.databinding.PropertyItemBinding

class PropertiesAdapter: RecyclerView.Adapter<PropertiesAdapter.PropertyHolder>()  {

    private val propertyList = ArrayList<PropertiesModel>()

    // adapter (шаблон по которому будет происходить заполнение данных)
    inner class PropertyHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = PropertyItemBinding.bind(item)
        init {
            handleShowView(item)
        }

        @SuppressLint("SetTextI18n")
        fun bind(property: PropertiesModel) = with(binding) {
            propertyTV.text = property.NAME + ": "
            propertyTVVal.text = property.VALUE
        }

        fun handleShowView(view: View) {
            if (adapterPosition > 4) {
                view.visibility = View.GONE
                return
            }
            view.visibility = View.VISIBLE
        }


    }

    /**
     * Создается элемент списка
     * надувает view и загружает его в память
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.property_item, parent, false)
        return PropertyHolder(view)
    }

    /**
     * Заполняется элемент списка
     */
    override fun onBindViewHolder(holder: PropertyHolder, position: Int) {
        holder.bind(propertyList[position])
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }

    fun addProperty(propertiesModel : PropertiesModel){
        propertyList.add(propertiesModel)
        notifyDataSetChanged()
    }





}