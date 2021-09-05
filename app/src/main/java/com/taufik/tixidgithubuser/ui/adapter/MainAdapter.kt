package com.taufik.tixidgithubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taufik.tixidgithubuser.data.model.UserResponseItem
import com.taufik.tixidgithubuser.databinding.ItemUsersBinding

class MainAdapter : PagingDataAdapter<UserResponseItem, MainAdapter.MainViewHolder>(WATCHLIST_COMPARATOR) {

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    class MainViewHolder(private val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: UserResponseItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(items.avatarUrl)
                    .into(imgUserProfile)

                tvUsernameProfile.text = items.login
                tvIdProfile.text = String.format("ID: %s", items.id.toString())
                tvUrlProfile.text = String.format("Url: %s", items.url)

                itemView.setOnClickListener {
                    Toast.makeText(itemView.context,
                        String.format("Username: %s", items.login) +
                                String.format("\nID: %s", items.id.toString()) +
                                String.format("\nUrl: %s", items.url),
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private val WATCHLIST_COMPARATOR = object : DiffUtil.ItemCallback<UserResponseItem>(){
            override fun areItemsTheSame(
                oldItem: UserResponseItem,
                newItem: UserResponseItem
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UserResponseItem,
                newItem: UserResponseItem
            ) = oldItem == newItem
        }
    }
}