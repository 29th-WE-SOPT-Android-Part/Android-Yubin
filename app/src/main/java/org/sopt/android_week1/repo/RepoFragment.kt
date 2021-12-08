package org.sopt.android_week1.repo

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.android_week1.RvItemDecoration
import org.sopt.android_week1.databinding.FragmentRepoBinding


class RepoFragment : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter
        binding.rvRepo.layoutManager = LinearLayoutManager(context)
        binding.rvRepo.addItemDecoration(RvItemDecoration(5, Color.parseColor("#DDE2E5")))
        repoAdapter.repoList.addAll(
            listOf(
                RepoData("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
                RepoData("기획 과제 레포지토리", "기획 파트 과제"),
                RepoData("디자인 과제 레포지토리", "디자인 파트 과제"),
                RepoData("ios 과제 레포지토리", "ios 파트 과제"),
                RepoData("웹 과제 레포지토리", "웹 파트 과제"),
                RepoData("서버 과제 레포지토리", "서버 파트 과제"),
                )
        )
        repoAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}