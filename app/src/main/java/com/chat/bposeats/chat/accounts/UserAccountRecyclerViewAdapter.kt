package com.chat.bposeats.chat.accounts


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chat.bposeats.BPChatApp
import com.chat.bposeats.R
import com.chat.bposeats.data.data.entity.ChatDialog
import com.chat.bposeats.data.data.entity.User
import kotlinx.android.synthetic.main.fragment_user_account.view.*


class UserAccountRecyclerViewAdapter(val presenter: AccountPresenter) : RecyclerView.Adapter<UserAccountRecyclerViewAdapter.ViewHolder>() {

    private val mValues: MutableList<User> = emptyList<User>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.phone
        holder.mView.setOnClickListener {
            presenter.startDialog(item)
        }
    }

    fun setData(newData: List<User>) {
        val postDiffCallback =
            CollectionDiffCallback(mValues, newData)
        val diffResult = DiffUtil.calculateDiff(postDiffCallback)
        mValues.clear()
        mValues.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    internal class CollectionDiffCallback(
        private val oldCollections: List<User>,
        private val newCollections: List<User>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldCollections.size
        }

        override fun getNewListSize(): Int {
            return newCollections.size
        }

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            return oldCollections[oldItemPosition].getId()
                .equals(newCollections[newItemPosition].getId())
        }

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            return oldCollections[oldItemPosition].equals(newCollections[newItemPosition])
        }

    }
}
