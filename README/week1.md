# [Week1] 과제 제출


### 1. 실행화면   
   <img width="80%" src="https://user-images.githubusercontent.com/65652094/136679179-271e8c9d-f87f-4d7a-8289-e60b6ba24f65.mp4"/>

### 2. 로직 설명
   
   
- **SignInActivity.kt**

  ```kotlin
  private lateinit var binding: ActivitySignInBinding
  
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)

        val id = binding.etId.text
        val pw = binding.etPassword.text

        binding.btLogin.setOnClickListener {
            if (id.toString().isEmpty() || pw.toString().isEmpty()) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${id}님 환영합니다", Toast.LENGTH_SHORT).show()
                startHomeActivity()
            }
        }
        binding.btSignup.setOnClickListener {
            startSignUpActivity()
        }
        setContentView(binding.root)
    }
    
    private fun startSignUpActivity() {
        val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun startHomeActivity() {
        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
        startActivity(intent)
    }
  ```   
   
   - binding = ActivitySignInBinding.inflate(layoutInflater)  
    : ViewBinding 사용
   
   - login 버튼 클릭 시
      1. id와 pw의 값 중 하나라도 비어있다면 Toast 메시지 띄움
      2. 모두 입력돼있다면 *startHomeActivity()*
   
   - signup 버튼 클릭 시  
    : *startSignupActivity()*   
<br/><br/>

- **SignUpActivity.kt**
  
  ```kotlin
  private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        val name = binding.etName.text
        val id = binding.etId.text
        val pw = binding.etPassword.text

        binding.btSignup.setOnClickListener {
            if (name.toString().isEmpty() || id.toString().isEmpty() || pw.toString().isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
            else
                finish()
        }
        setContentView(binding.root)
    }
  ```    
        
  - signup 버튼 클릭 시
    1. name, id, pw의 값 중 하나라도 비어있다면 Toast 메시지 띄움
    2. 모두 입력돼있다면 *finish()*   
   </br>
   
 - **signin_et_bg.xml**

   ```kotlin
   <?xml version="1.0" encoding="utf-8"?>
   <selector xmlns:android="http://schemas.android.com/apk/res/android">
      <item android:state_focused="true">
         <shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle">
            <stroke android:width="1dp" android:color="@color/sopt_pink" />
            <corners android:radius="4dp" />
            <solid android:color="#FFFFFF" />
        </shape>
    </item>

    <item android:state_focused="false">
      <shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle">
            <stroke
                android:width="1dp"
                android:color="@color/sopt_darkgray" />
            <corners android:radius="4dp" />
            <solid
                android:color="#FFFFFF" />
      </shape>
   </item>
   </selector>
   ```   
             
   - editText 클릭 시 색을 변경해줌
</br>

- **signin_bt_pink_bg.xml**

   ```kotlin
   <?xml version="1.0" encoding="utf-8"?>
   <layer-list xmlns:android="http://schemas.android.com/apk/res/android" >
      <item
        android:bottom="1dp"
        android:left="1dp"
        android:right="1dp"
        android:top="1dp">
        <shape android:shape="rectangle" >
            <stroke android:color="@color/sopt_pink"
                android:width="2dp"/>
            <solid android:color="@color/sopt_pink" />
            <corners android:radius="5dp" />
        </shape>
      </item>
   </layer-list>
   ```     
            
   - 버튼 색을 변경하기 위해 button의 background에 추가
</br>

### 3. 배운 내용   
   - android:inputType="textPassword"   
      : editText에서 inputType 속성을 이용해 입력 방법 유형을 지정할 수 있다.    
         
   - button 스타일 적용    
      : 버튼의 background로 스타일을 적용해 모양과 색을 바꿀 수 있다.
      버튼 테두리는 *style="@style/Widget.AppCompat.Button.Borderless"* 를 이용해 지울 수 있다.
      
   - ViewBinding 사용 시 주의할 점
      ```kotlin
      buildFeatures {
        viewBinding true
      }
      ```   
      : build.gradle에 위 코드를 추가해야 ViewBinding을 사용할 수 있다.
