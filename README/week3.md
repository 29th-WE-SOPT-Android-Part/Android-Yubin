# [Week3] 과제 제출



### 1. 실행화면  

- level1

  <img width="30%" src="https://user-images.githubusercontent.com/65652094/139176778-9fa91f79-2c66-4036-af83-4df67fe2703d.gif"/> 

- level2_2, level3_1

  ### <img width="30%" src="https://user-images.githubusercontent.com/65652094/139625802-6f725962-24a5-4875-bd03-a6002e9c656d.gif"/>

</br>

---

### 2. 로직 설명

**:round_pushpin: level1**

- **MainActivity.kt**   

  ```kotlin
  	private fun initAdapter() {
          val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
  
          mainViewPagerAdapter = MainViewPagerAdapter(this)
          mainViewPagerAdapter.fragments.addAll(fragmentList)
  
          binding.vpMain.adapter = mainViewPagerAdapter
  	}
  ```

  ViewPager2와 Adapter 연동

  

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

  BottomNavigation과 ViewPager2 연동



- **ProfileFragment.kt**

  ```kotlin
      private fun initProfile() {
          Glide.with(this)
              .load(R.drawable.profile)
              .circleCrop()
              .into(binding.ivProfile)
      }
  ```

  Glide를 통해 프로필 사진을 넣어줌. `.circleCrop()` 기능을 이용해 모양 적용.

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



**:round_pushpin:level2_2**

- **AndroidManifest.xml**

  ```kotlin
  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="org.sopt.android_week1">
      <uses-permission android:name="android.permission.INTERNET" />
      <application...>
  </manifest>
  ```

  안드로이드 앱에서 인터넷에 접속하기 위해 `<uses-permission android:name="android.permission.INTERNET" />` 코드를 AndroidManifest.xml에 추가



- **FollowerData.kt**

  ```kotlin
  data class FollowerData(
      val profile : String,
      val name: String,
      val introduction : String
  )
  ```

  profile의 type 변경



- **FollowerFragment.kt**

  ```kotlin
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
  ```

  Drawable에 있는 이미지 파일 대신 url을 넣어줌



**:round_pushpin:level3_1**

- **build.gradle(Module: ..)** 

  ```kotlin
  buildFeatures {
          dataBinding true
      }
  ```

  dataBinding 사용을 위해 추가함



- **ProfileBindingAdapter.kt**

  ```kotlin
  object ProfileBindingAdapter {
      @JvmStatic
      @BindingAdapter("app:ProfileImg")
      fun setImage (imageview : ImageView, img: String){
          Glide.with(imageview.context)
              .load(img)
              .circleCrop()
              .into(imageview)
      }
  }
  ```

  - object : Binding Adapter는 메모리상에 올려서 사용해야 하기 때문에 Object로 생성
  - @JvmStatic : 전역 변수의 Getter Setter를 정적 함수로 설정하는 어노테이션
  - @BindingAdapter : 괄호 안에 원하는 메소드 이름을 지어주면 됨
  - setImage : 이것도 원하는 메소드 이름으로 지어주면 됨

  참고 : https://todaycode.tistory.com/53



- **item_follower_list.xml**

  ```xml
  <layout
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools">
  
      <data>
          <variable
              name="followerViewModel"
              type="org.sopt.android_week1.FollowerData" />
      </data>
  
      <androidx.constraintlayout.widget.ConstraintLayout .../>
  </layout>
  ```

  - `<layout>` 과`<data>` 추가

  - name : 이 ViewModel을 가리키는 변수명
  - type : `<data>` 태그로 결합할 객체의 루트

  ```xml
  <TextView
              android:id="@+id/tv_follower_name"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_marginStart="22dp"
              android:fontFamily="@font/noto_sans_kr"
              android:includeFontPadding="false"
              android:textColor="@color/sopt_gray1"
              android:textFontWeight="700"
              android:textSize="16sp"
              app:layout_constraintBottom_toTopOf="@+id/tv_follower_intro"
              app:layout_constraintStart_toEndOf="@id/iv_follower_profile"
              app:layout_constraintTop_toTopOf="@id/iv_follower_profile"
              tools:text="@{followerViewModel.name}" />
  ```

  `@{}`를 이용해 변수를 사용함.

  ```xml
  <ImageView
              android:id="@+id/iv_follower_profile"
              android:layout_width="49dp"
              android:layout_height="49dp"
              app:ProfileImg="@{followerViewModel.profile}"
              app:layout_constraintBottom_toBottomOf="parent"
              app:layout_constraintDimensionRatio="1:1"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toTopOf="parent" />
  ```

  ProfileBindingAdapter에서 `@BindingAdapter("app:ProfileImg")`로 지정했기 때문에 `app:ProfileImg="@{followerViewModel.profile}"` 와 같이 씀.



- **FollowerAdapter.kt**

  ```kotlin
  fun onBind(data : FollowerData) {
              with(binding) {
                  followerViewModel = data
              }
      //...
  }
  ```

  data binding 활용

</br>

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
