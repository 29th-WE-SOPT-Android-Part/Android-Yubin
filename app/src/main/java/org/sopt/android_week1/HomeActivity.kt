package org.sopt.android_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.android_week1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var position = FOLLOWER_POSITION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        initTransactionEvent()

        setContentView(binding.root)
    }

    private fun initTransactionEvent() {
        val fragment1 = FollowerFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_home, fragment1).commit()

        clickRepoBtn()
        clickFollowerBtn()
    }

    private fun clickRepoBtn() {
        binding.btRepo.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (position) {
                FOLLOWER_POSITION -> {
                    transaction.replace(R.id.container_home, RepoFragment())
                    position = REPO_POSITION
                }
            }
            transaction.commit()
        }
    }

    private fun clickFollowerBtn() {
        binding.btFollower.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (position) {
                REPO_POSITION -> {
                    transaction.replace(R.id.container_home, FollowerFragment())
                    position = FOLLOWER_POSITION
                }
            }
            transaction.commit()
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}