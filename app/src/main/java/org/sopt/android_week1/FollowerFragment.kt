package org.sopt.android_week1

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sopt.android_week1.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment(), ItemDragListener {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)

        initAdapter()

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.rvFollower)

        return binding.root
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(this)
        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.layoutManager = LinearLayoutManager(context)
        binding.rvFollower.addItemDecoration(RvItemDecoration(5, Color.parseColor("#DDE2E5")))

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("https://i.pinimg.com/564x/79/2a/b9/792ab9d9c0312b21912e91d9e09deef7.jpg", "문다빈", "안드로이드 파트장"),
                FollowerData("https://i.pinimg.com/236x/74/87/ad/7487ade70b50f76d44540d2fe1faf885.jpg", "김현아", "기획 파트장"),
                FollowerData("https://i.pinimg.com/236x/16/0e/90/160e90ad6aded4c8d8ca4510b207ad20.jpg", "이성현", "디자인 파트장"),
                FollowerData("https://i.pinimg.com/236x/c0/ef/17/c0ef1718a2b0e68a43ddaf262272228c.jpg", "장혜령", "ios 파트장"),
                FollowerData("https://i.pinimg.com/564x/79/2a/b9/792ab9d9c0312b21912e91d9e09deef7.jpg", "김의진", "웹 파트장"),
                FollowerData("https://i.pinimg.com/236x/74/87/ad/7487ade70b50f76d44540d2fe1faf885.jpg", "김우영", "서버 파트장"),
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}