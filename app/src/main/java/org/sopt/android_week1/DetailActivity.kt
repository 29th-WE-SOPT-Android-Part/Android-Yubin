package org.sopt.android_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.sopt.android_week1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        initProfile()
        initDetail()

        setContentView(binding.root)
    }

    private fun initProfile() {
        intent?.let {
            val profile = it.getIntExtra("profile", R.drawable.memo_1)
            Glide.with(this)
                .load(profile)
                .into(binding.ivProfile)
        }
    }

    private fun initDetail() {
        intent?.let {
            val name = it.getStringExtra("name")
            binding.tvName.text = name
            when(name) {
                "문다빈" -> binding.tvDetail.text = getString(R.string.detail1)
                "김현아" -> binding.tvDetail.text = getString(R.string.detail2)
                "이성현" -> binding.tvDetail.text = getString(R.string.detail3)
                "장혜령" -> binding.tvDetail.text = getString(R.string.detail4)
                "김의진" -> binding.tvDetail.text = getString(R.string.detail5)
                "김우영" -> binding.tvDetail.text = getString(R.string.detail6)
            }
        }
    }
}