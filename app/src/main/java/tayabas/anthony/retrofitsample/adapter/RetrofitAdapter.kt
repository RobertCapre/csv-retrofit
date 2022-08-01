package tayabas.anthony.retrofitsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tayabas.anthony.retrofitsample.R
import tayabas.anthony.retrofitsample.databinding.ItemUserBinding
import tayabas.anthony.retrofitsample.model.Data
import tayabas.anthony.retrofitsample.model.ResponseListUsers

class RetrofitAdapter(
    private val dataset: List<Data>
): RecyclerView.Adapter<RetrofitAdapter.RetrofitViewHolder>() {


    class RetrofitViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {
        val binding =  ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return RetrofitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {
        val item = dataset[position]
        holder.itemView.apply {
            with(holder.binding)  {
                val  name = "${item.firstName} ${item.lastName}"
                tvFirstLastName.text = "Name: $name"
                tvEmail.text = item.email
            }
            setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private var onItemClickListener: ((Data) -> Unit)? = null

    fun  setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}