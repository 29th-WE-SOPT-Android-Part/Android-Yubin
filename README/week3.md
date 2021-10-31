# [Week3] 과제 제출



### 1. 실행화면  

### <img width="30%" src="https://user-images.githubusercontent.com/65652094/139176778-9fa91f79-2c66-4036-af83-4df67fe2703d.gif"/> 



---

### 2. 로직 설명

- **MainActivity.kt**   

  ```kotlin
  	private fun initAdapter() {
          val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
  
          mainViewPagerAdapter = MainViewPagerAdapter(this)
          mainViewPagerAdapter.fragments.addAll(fragmentList)
  
          binding.vpMain.adapter = mainViewPagerAdapter
  	}
  ```

  - ViewPager2와 Adapter 연동

  

  ```kotlin
  	private fun initBottomNavigation() {
          binding.vpMain.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
              override fun onPageSelected(position: Int) {
                  binding.bnvMenu.menu.getItem(position).isChecked = true
              }
          })
  
          binding.bnvMenu.setOnNavigationItemSelectedListener setOnItemSelectedListener@{
              when(it.itemId) {
                  R.id.menu_profile -> {
                      binding.vpMain.currentItem = PROFILE_FRAGMENT
                      return@setOnItemSelectedListener true
                  }
                  R.id.menu_home -> {
                      binding.vpMain.currentItem = HOME_FRAGMENT
                      return@setOnItemSelectedListener true
                  }
                  else -> {
                      binding.vpMain.currentItem = CAMERA_FRAGMENT
                      return@setOnItemSelectedListener true
                  }
              }
          }
      }
  
      companion object {
          const val PROFILE_FRAGMENT = 0
          const val HOME_FRAGMENT = 1
          const val CAMERA_FRAGMENT = 2
      }
  ```

  - BottomNavigation과 ViewPager2 연동



- **ProfileFragment.kt**

  ```kotlin
      private fun initProfile() {
          Glide.with(this)
              .load(R.drawable.profile)
              .circleCrop()
              .into(binding.ivProfile)
      }
  ```

  - Glide를 통해 프로필 사진을 넣어줌. `.circleCrop()` 기능을 이용해 모양 적용.

  ```kotlin
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
  ```

  - `childFragmentManager.beginTransaction().add(R.id.container_profile, fragment1).commit()`를 통해 첫 화면에 보여질 fragment를 add함.
  - 버튼 클릭 시 changeFragment()를 실행하면서  `childFragmentManager.beginTransaction()
                .replace(R.id.container_profile, fragment)
                .commit()`를 수행.



- **FollowerFragment.kt**

  ```kotlin
  	private fun initAdapter() {
          followerAdapter = FollowerAdapter(this)
          binding.rvFollower.adapter = followerAdapter
          binding.rvFollower.layoutManager = LinearLayoutManager(context)
          binding.rvFollower.addItemDecoration(RvItemDecoration(5, Color.parseColor("#DDE2E5")))
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
  ```

  `binding.rvFollower.layoutManager = LinearLayoutManager(context)` 를 통해 layout을 지정해줌.



---

### 3. 배운 내용

- font 적용

  - res 폴더에 font 폴더를 생성한 후, 다운받은 폰트들을 넣는다.
  - FontFamily 파일을 만들고 View에 적용한다.
  - 다운받은 폰트의 파일명은 소문자로 시작해야한다.
  - `android:includeNotFontPadding=false` 를 넣어준다.

- Button에 selector 활용

  ```kotlin
  <selector xmlns:android="http://schemas.android.com/apk/res/android">
      <item android:state_selected="true">
          <shape android:shape="rectangle">
              <corners android:radius="5dp" />
              <solid android:color="@color/main_orange" />
          </shape>
      </item>
      <item android:state_selected="false">
          <shape android:shape="rectangle">
              <corners android:radius="5dp" />
              <solid android:color="@color/sopt_gray6" />
          </shape>
      </item>
  </selector>
  ```

  button의 background로 위의 파일을 지정해줌

  

