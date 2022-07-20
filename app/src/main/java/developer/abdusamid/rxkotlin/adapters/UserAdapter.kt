package developer.abdusamid.rxkotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import developer.abdusamid.rxkotlin.dao.OnItemCLickListener
import developer.abdusamid.rxkotlin.databinding.ItemRvBinding
import developer.abdusamid.rxkotlin.entity.User

class UserAdapter(var onItemCLickListener: OnItemCLickListener) :
    ListAdapter<User, UserAdapter.VH>(MyDiffUtil()) {
    inner class VH(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User) {
            itemRv.tv1.text = user.userName
            itemRv.tv2.text = user.password
            itemRv.root.setOnClickListener {
                onItemCLickListener.onItemClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }
}