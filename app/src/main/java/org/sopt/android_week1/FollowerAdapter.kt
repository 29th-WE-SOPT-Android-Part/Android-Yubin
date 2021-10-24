package org.sopt.android_week1

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.android_week1.databinding.ItemFollowerListBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size

    class FollowerViewHolder(private val binding: ItemFollowerListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : FollowerData) {
            Glide.with(itemView)
                .load(data.profile)
                .into(binding.ivFollowerProfile)
            binding.tvFollowerName.text = data.name
            binding.tvFollowerIntro.text = data.introduction

            binding.root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("profile", data.profile)
                intent.putExtra("name", data.name)
                it.context.startActivity(intent)
            }
        }
    }
}