# [Week2] 과제 제출


### 1. 실행화면   
<img width="80%" src="https://user-images.githubusercontent.com/65652094/138455909-d2b68567-539a-4204-a70c-88a7d52f56be.mp4"/>

### 2. 로직 설명
- **HomeActivity.kt**   
  ```kotlin
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
  ```
  - supportFragmentManager로 FragmentManager 호출
  - beginTransaction()을 통해 add 작업
  - 팔로워 목록 버튼과 레포지토리 목록 버튼  클릭 시 각각 clickRepoBtn(), clickFollowerBtn() 호출

  ```kotlin
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
    ```
    - beginTransaction()을 통해 교체 작업
    - commit()을 통해 작업 수행

- **item_follower_list.xml**
  ```kotlin
  <androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    android:padding="15dp"
    android:background="@drawable/item_bg">

    <ImageView
        android:id="@+id/iv_follower_profile"
        android:layout_width="70dp"
        android:layout_height="70dp"
        app:layout_constraintDimensionRatio="1:1"
        android:src="@drawable/memo_1"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/tv_follower_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        tools:text="이름"
        android:textSize="18sp"
        android:textColor="@color/sopt_darkgray"
        android:textStyle="bold"
        android:layout_marginStart="20dp"
        app:layout_constraintTop_toTopOf="@id/iv_follower_profile"
        app:layout_constraintBottom_toTopOf="@+id/tv_follower_intro"
        app:layout_constraintStart_toEndOf="@id/iv_follower_profile" />

    <TextView
        android:id="@+id/tv_follower_intro"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        tools:text="자기 소개 텍스트~~~~~~~~"
        android:textSize="14sp"
        android:textColor="@color/sopt_darkgray"
        app:layout_constraintTop_toBottomOf="@id/tv_follower_name"
        app:layout_constraintBottom_toBottomOf="@id/iv_follower_profile"
        app:layout_constraintStart_toStartOf="@id/tv_follower_name" />
  </androidx.constraintlayout.widget.ConstraintLayout>
  ```   
  - 리스트에 반복적으로 보여질 아이템 레이아웃
  - 각 아이템 테두리를 `android:background="@drawable/item_bg"`로 설정
  	- *item_bg.xml*   
  	  ```kotlin
    	<item
        android:bottom="1dp"
        android:left="1dp"
        android:right="1dp"
        android:top="1dp">
        <shape android:shape="rectangle" >
            <stroke android:color="@color/sopt_pink"
                android:width="2dp"/>
            <corners android:radius="8dp" />
        </shape>
    	</item>
  	  ```
			
 - **FollowerData.kt**
   ```kotlin
	 data class FollowerData(
	 	val profile : Int,
    val name: String,
    val introduction : String
		)
   ```
	- 리스트에 들어갈 data를 담는 클래스
	- profile : iv_follower_profile
	- name : tv_follower_name
	- introduction : tv_follower_intro   
   
 - **FollowerAdapter.kt**
 	```kotlin
	class FollowerViewHolder(private val binding: ItemFollowerListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : FollowerData) {
            Glide.with(itemView)
                .load(data.profile)
                .into(binding.ivFollowerProfile)
            binding.tvFollowerName.text = data.name
            binding.tvFollowerIntro.text = data.introduction
        }
    }
 	```
	- FollowerAdapter에서 전달받은 데이터를 item_follower_list에 Bind해주는 역할   
	- glide를 통해 이미지를 가져와서 넣음   
  	```kotlin
		override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerAdapter.FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

  	override fun getItemCount(): Int = followerList.size
  	```
		- Adapter에서 오버라이드해줘야하는 함수 3가지
		- onBindViewHolder:  onbind를 호출해 ViewHolder가 가진 View에 Adapter로부터 전달받은 data를 붙여줌
		- onCreateViewHolder: ViewHolder 생성
</br>

 - **FollowerFragment.kt**
   ```kotlin
	 	private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter
   ```
	 - 죽은 객체를 참조하는 것을 방지하기 위함
	 ```kotlin
	 override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()		// Adapter 초기화
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
		```
		- initAdapter() : Adapter에서 List로 보여줄 데이터를 넣어줌
		- notifyDataSetChanged()를 통해 Adapter에 데이터 갱신을 알려줌

 - **activity_home.xml**
   ```kotlin
	 <Button
        android:id="@+id/bt_follower"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="@string/home_bt_follower"
        android:textSize="15sp"
        android:textColor="@color/white"
        android:background="@drawable/home_bt_follower_bg"
        android:padding="10dp"
        android:layout_marginTop="70dp"
        android:layout_marginStart="40dp"
        android:layout_marginEnd="15dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@id/bt_repo"/>

    <Button
        android:id="@+id/bt_repo"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="@string/home_bt_repo"
        android:textSize="15sp"
        android:textColor="@color/white"
        android:background="@drawable/home_bt_repo_bg"
        android:padding="10dp"
        android:layout_marginStart="15dp"
        android:layout_marginEnd="40dp"
        app:layout_constraintTop_toTopOf="@id/bt_follower"
        app:layout_constraintBottom_toBottomOf="@id/bt_follower"
        app:layout_constraintLeft_toRightOf="@id/bt_follower"
        app:layout_constraintRight_toRightOf="parent" />

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/container_home"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:padding="15dp"
        tools:layout="@layout/fragment_repo"
        app:layout_constraintTop_toBottomOf="@id/bt_follower"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
   ```
	 - container_home
	   - RecyclerView를 배치할 레이아웃
	   - 버튼 클릭 시 fragment가 교체됨
</br>

 - **fragment_repo.xml**
   ```kotlin
	     <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_repo"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
        tools:itemCount="6"
        app:spanCount="2"
        tools:listitem="@layout/item_repo_list"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
   ```
	 - repository 목록은 `app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"`를 통해 만듦


### 3. 배운 내용
 - Fragment View의 생명주기가 Fragment 자체의 생명주기보다 짧아서 View는 죽어도 Fragment 자체는 살아있을 수 있다.   
   ```kotlin
	 private var _binding: FragmentFollowerBinding? = null
   private val binding get() = _binding!!
	 ```
	 nullable한 변수로 선언한 후 Kotlin property를 활용해 binding 변수의 getter를 정의함으로써 해결할 수 있다.
 - ellipsize 속성 사용시 maxLength를 함께 사용하면 적용되지 않는다.
   ```
   android:ems = "10"
   android:maxLines = "1"
   android:ellipsize="end"
   ```
   와 같이 ems 속성을 대신 사용하면 된다. ems는 공백 미포함, maxLength는 공백 포함이라는 차이가 있다.
 - RecyclerView의 layoutManger 속성이 GridLayoutManager일 경우 spanCount 속성을 이용해 한 줄에 들어갈 grid item의 개수를 정할 수 있다.   

