package developer.abdusamid.rxkotlin.adapters

import androidx.recyclerview.widget.DiffUtil
import developer.abdusamid.rxkotlin.entity.User

class MyDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.equals(newItem)
    }

}