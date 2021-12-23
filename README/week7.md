# [week7] 과제 제출



## **:one:** 실행 화면

<img width="30%" src="https://user-images.githubusercontent.com/65652094/146524847-0888fdba-92bc-4418-ba06-c02479d38a03.gif">



## **:two:** 로직 설명

- 온보딩 화면 만들기

  - **`build.gradle`**

    ```kotlin
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    ```

    - 라이브러리를 추가한다.

  

  - **`nav_onboarding.xml`**

    <img src="https://user-images.githubusercontent.com/65652094/146541596-eaf3a151-10f6-4987-af45-d0640e83e3c3.png">

    ```xml
    <navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/nav_onboarding"
        app:startDestination="@id/onBoardingFragment1">
        <fragment
            android:id="@+id/onBoardingFragment1"
            android:name="org.sopt.android_week1.onboarding.OnBoardingFragment1"
            android:label="첫 번째 화면"
            tools:layout="@layout/fragment_on_boarding1">
            <action
                android:id="@+id/action_onBoardingFragment1_to_onBoardingFragment2"
                app:destination="@id/onBoardingFragment2" />
        </fragment>
    	//...
        <fragment
            android:id="@+id/onBoardingFragment3"
            android:name="org.sopt.android_week1.onboarding.OnBoardingFragment3"
            android:label="세 번째 화면"
            tools:layout="@layout/fragment_on_boarding3" >
            <action
                android:id="@+id/action_onBoardingFragment3_to_onBoardingFragment1"
                app:destination="@id/onBoardingFragment1"
                app:popUpTo="@+id/onBoardingFragment1"
                app:popUpToInclusive="true"/>
        </fragment>
    </navigation>
    ```

    - onBoardingFragment1,2,3을 만든 뒤 드래그 앤 드롭 방식으로 프래그먼트의 전환을 지정했다.
    - `app:popUpTo`와 `app:popUpToInclusive` 를 이용해 fragment3에서 뒤로 가기를 눌렀을 때 fragment1로 이동하도록 만들었다.

<br/>

- SharedPrefences 활용해 자동로그인/자동로그인 해제

  - **`SOPTSharedPreference.kt`**

    ```kotlin
    object SOPTSharedPreferences {
        private const val STORAGE_KEY = "USER_AUTH"
        private const val AUTO_LOGIN = "AUTO_LOGIN"
    
        fun getAutoLogin(context: Context): Boolean {
            val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
            return preferences.getBoolean(AUTO_LOGIN, false)
        }
    
        fun setAutoLogin(context: Context, value: Boolean) {
            val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
            preferences.edit()
                .putBoolean(AUTO_LOGIN, value)
                .apply()
        }
    
        fun removeAutoLogin(context: Context) {
            val preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
            preferences.edit()
                .remove(AUTO_LOGIN)
                .apply()
        }
    }
    ```

  - **`OnBoardingActivity.kt`**

    ```kotlin
    	private fun isAutoLogin() {
            if(SOPTSharedPreferences.getAutoLogin(this)) {
               shortToast("자동로그인 되었습니다")
               startActivity(Intent(this@OnBoardingActivity,MainActivity::class.java))
               finish()
            }
        }
    ```

    - 자동로그인이 설정되어있을 경우 MainActivity로 바로 이동한다.'

  - **`SignInActivity.kt`**

    ```kotlin
    	private fun clickBtnCheck() {
            binding.ibCheck.setOnClickListener {
                binding.ibCheck.isSelected = !binding.ibCheck.isSelected
                SOPTSharedPreferences.setAutoLogin(this, binding.ibCheck.isSelected)
            }
        }
    ```

    - 자동 로그인 버튼 체크 시 `SOPTSharedPreferences.setAutoLogin`으로 자동로그인을 설정한다.

  - **`SettingActivity.kt`**

    ```kotlin
        private fun initSwitch() {
            binding.switchAutoLogin.isChecked = SOPTSharedPreferences.getAutoLogin(this)
        }
    
        private fun clickSwitch() {
            val switch = binding.switchAutoLogin
            switch.setOnClickListener {
                SOPTSharedPreferences.setAutoLogin(this, switch.isSelected)
            }
        }
    ```

    - `initSwitch` : switch의 상태를 지정한다.
    - `clickSwitch` : switch 클릭 시 AutoLogin 여부를 변경한다.



- NavigationComponent와 ToolBar 연동

  - **`activity_on_boarding.xml`**

    ```xml
        <androidx.appcompat.widget.Toolbar
            android:id="@+id/tb_label"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@drawable/shadow"
            android:labelFor="@id/container_onboarding"
            app:layout_constraintTop_toTopOf="parent" />
    ```

  - `OnBoardingActivity.kt`

    ```kotlin
    	private fun initTopBar() {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_onboarding) as NavHostFragment
            val navController = navHostFragment.navController
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            binding.tbLabel.setupWithNavController(navController, appBarConfiguration)
        }
    ```

    - 참고 : https://rkdxowhd98.tistory.com/154



- 패키징 방식

  <img src="https://user-images.githubusercontent.com/65652094/146541885-9f9a49de-cb08-4e2e-aa9a-8f2b97235dec.png">

  - 크게 data, ui, util로 나누었다.

  - ui에서 둘 이상의 파일이 쓰이는 Activity의 경우 패키지로 묶어서 관리했다.
  - `Util.kt` 에는 자주 쓰는 Toast 코드를 넣어놨다.

<br/>

## **:three:** 배운 내용

- **`activity_on_boarding.xml`**

  ```xml
      <androidx.fragment.app.FragmentContainerView
          android:id="@+id/container_onboarding"
          android:name="androidx.navigation.fragment.NavHostFragment"
          android:layout_width="match_parent"
          android:layout_height="0dp"
          app:layout_constraintTop_toBottomOf="@id/tb_label"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:defaultNavHost="true"
          app:navGraph="@navigation/nav_onboarding" />
  ```

  - `android:name` : NavHost 지정을 위한 NavHostFragment 클래스 설정.
  - `app:defaultNavhost` : NavHostFragment가 백버튼 로직을 가로챌 수 있게 해주는 속성.
  -  `app:navGraph` : NavHostFragment를 Navigation Grap와 연결하는 속성.

  </br>

- **`OnBoardingFragment3.kt`**

  ```kotlin
  private lateinit var callback: OnBackPressedCallback
  
      override fun onAttach(context: Context) {
          super.onAttach(context)
          callback = object : OnBackPressedCallback(true) {
              override fun handleOnBackPressed() {
                  findNavController().navigate(R.id.action_onBoardingFragment3_to_onBoardingFragment1)
              }
          }
          requireActivity().onBackPressedDispatcher.addCallback(this, callback)
      }
  
      override fun onDetach() {
          super.onDetach()
          callback.remove()
      }
  ```

  - `Fragment`에서의 `Back Press` 처리

  - `OnBackPressedCallback` 추상 클래스의 `handleOnBackPressed()`를 재정의하는 것이다.

    참고 : https://readystory.tistory.com/186
