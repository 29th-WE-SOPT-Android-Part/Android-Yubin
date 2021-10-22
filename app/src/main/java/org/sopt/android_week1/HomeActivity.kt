package org.sopt.android_week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.android_week1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var position = FIRST_POSITION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()
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
                FIRST_POSITION -> {
                    transaction.replace(R.id.container_home, RepoFragment())
                    position = SECOND_POSITION
                }
            }
            transaction.commit()
        }
    }

    private fun clickFollowerBtn() {
        binding.btFollower.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (position) {
                SECOND_POSITION -> {
                    transaction.replace(R.id.container_home, FollowerFragment())
                    position = FIRST_POSITION
                }
            }
            transaction.commit()
        }
    }

    companion object {
        const val FIRST_POSITION = 1
        const val SECOND_POSITION = 2
    }
}