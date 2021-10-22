package org.sopt.android_week1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.android_week1.databinding.ItemRepoListBinding

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    val repoList = mutableListOf<RepoData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        val binding = ItemRepoListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoAdapter.RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    class RepoViewHolder(private val binding: ItemRepoListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : RepoData) {
            binding.tvRepoTitle.text = data.title
            binding.tvRepoContent.text = data.content
        }
    }
}