package org.sopt.android_week1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.android_week1.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(R.drawable.memo_1, "문다빈", "안드로이드 파트장"),
                FollowerData(R.drawable.memo_3, "김현아", "기획 파트장"),
                FollowerData(R.drawable.memo_4, "이성현", "디자인 파트장"),
                FollowerData(R.drawable.memo_2, "장혜령", "ios 파트장"),
                FollowerData(R.drawable.memo_1, "김의진", "웹 파트장"),
                FollowerData(R.drawable.memo_3, "김우영", "서버 파트장"),
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}