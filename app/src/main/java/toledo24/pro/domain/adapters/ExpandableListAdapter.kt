package toledo24.pro.domain.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.appcompat.widget.AppCompatTextView
import toledo24.pro.R

class ExpadableListAdapter(
    val context: Context,
    val listofHeaderData: List<String>,
    val listOfChildData: HashMap<String, List<String>>
): BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return listofHeaderData.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listOfChildData[listofHeaderData[groupPosition]]!!.size
    }

    override fun getGroup(position: Int): Any {
        return listofHeaderData[position]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listOfChildData[listofHeaderData[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
       return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {

        val headerTitle = getGroup(groupPosition) as String

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_header, parent, false)
        val listHederText = view.findViewById<AppCompatTextView>(R.id.list_header_text) as AppCompatTextView

        listHederText.setTypeface(null, Typeface.BOLD)
        listHederText.text = headerTitle

        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, converView: View?, parent: ViewGroup?): View {
        val childText = getChild(groupPosition, childPosition) as String

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_header, parent, false)

        val listItemText = view.findViewById<AppCompatTextView>(R.id.list_header_text) as AppCompatTextView
        listItemText.text = childText
        return view
    }

    override fun isChildSelectable(hederPosition: Int, childrenPosition: Int): Boolean {
        return true
    }
}