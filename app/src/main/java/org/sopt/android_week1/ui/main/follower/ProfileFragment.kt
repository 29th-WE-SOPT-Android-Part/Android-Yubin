package org.sopt.android_week1.ui.main.follower

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import org.sopt.android_week1.R
import org.sopt.android_week1.databinding.FragmentProfileBinding
import org.sopt.android_week1.ui.main.profile.repo.RepoFragment


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initProfile()
        initTransactionEvent()
        clickBtnSetting()

        return binding.root
    }

    private fun clickBtnSetting() {
        binding.ibSetting.setOnClickListener {
            startActivity(Intent(requireActivity(), SettingActivity::class.java))
        }
    }
    private fun initProfile() {
        Glide.with(this)
            .load(R.drawable.profile)
            .circleCrop()
            .into(binding.ivProfile)
    }

    private fun initTransactionEvent() {
        val fragment1 = FollowerFragment()
        binding.btFollower.isSelected = true
        childFragmentManager.beginTransaction().add(R.id.container_profile, fragment1).commit()

        binding.btRepository.setOnClickListener {
            changeFragment(RepoFragment(), REPOSITORY_POSITION)
        }
        binding.btFollower.setOnClickListener {
            changeFragment(FollowerFragment(), FOLLOWER_POSITION)
        }
    }

    private fun changeFragment(fragment: Fragment, position: Int) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_profile, fragment)
            .commit()

        when (position) {
            FOLLOWER_POSITION -> {
                binding.btRepository.isSelected = false
                binding.btFollower.isSelected = true
            }
            REPOSITORY_POSITION -> {
                binding.btRepository.isSelected = true
                binding.btFollower.isSelected = false
            }
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPOSITORY_POSITION = 2
    }
}