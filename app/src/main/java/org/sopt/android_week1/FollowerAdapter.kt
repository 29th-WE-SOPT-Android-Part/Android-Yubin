package org.sopt.android_week1

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.android_week1.databinding.ItemFollowerListBinding

class FollowerAdapter(private val listener: ItemDragListener) : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemActionListener {
    val followerList = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return FollowerViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size

    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }
        val fromItem = followerList.removeAt(from)
        followerList.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        followerList.removeAt(position)
        notifyItemRemoved(position)
    }

    @SuppressLint("ClickableViewAccessibility")
    class FollowerViewHolder(private val binding: ItemFollowerListBinding, listener: ItemDragListener)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clItemFollower.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

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