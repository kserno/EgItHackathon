package pandas.com.egithackathon.atmlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import pandas.com.egithackathon.BR
import pandas.com.egithackathon.R
import pandas.com.egithackathon.databinding.ItemAtmBinding
import pandas.com.egithackathon.model.AtmModel

/**
 *  Created by filipsollar on 19.10.18
 */
class AtmListAdapter(
        val listener: Listener
): RecyclerView.Adapter<AtmListAdapter.AtmItemViewHolder>() {

    private var items: List<AtmModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtmItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_atm, parent, false)

        return AtmItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AtmItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(items: List<AtmModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class AtmItemViewHolder(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.onClick(item)
            }

        }

        var item: AtmModel? = null

        val binding = DataBindingUtil.bind<ItemAtmBinding>(itemView)
                .apply { this!!.viewModel = AtmItemViewModel() }

        fun bind(item : AtmModel) {
            this.item = item

            binding?.tvAddress?.text = item.address
            binding?.tvDistance?.text = item.distance
            binding?.tvName?.text = item.objectId

        }


    }

    interface Listener {
        fun onClick(model: AtmModel?)
    }

}