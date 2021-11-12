# [Week4] 과제 제출



### 1. 실행화면  

- POSTMAN

  <img src="https://user-images.githubusercontent.com/65652094/141441635-13d9a0fa-7574-464f-8c06-60b1605b976c.png">

  <img src="https://user-images.githubusercontent.com/65652094/141441797-c3911af0-457d-404a-9b92-8905879e3ef8.png">

- 실행화면

  <img width="30%" src="https://user-images.githubusercontent.com/65652094/141429791-50b604dd-69f5-4eab-aaa7-811090edaa15.gif"/>

  

### 2. 로직 설명

- **build.gradle**

  ```kotlin
      implementation "com.squareup.retrofit2:retrofit:2.9.0"
      implementation "com.squareup.retrofit2:converter-gson:2.9.0"
      implementation 'com.google.code.gson:gson:2.8.8'
  ```

  - 서버 연결을 위한 Retrofit2
  - Retrofit2에서 gson 사용을 위한 컨버터
  - gson



- **AndroidManifest.xml**

  ```xml-dtd
      <uses-permission android:name="android.permission.INTERNET" />
  
      <application
          android:usesCleartextTraffic="true"
  ```

  - 인터넷 접속 권한 부여
  - http 통신을 위해 `android:usesCleartextTraffic` 속성을 true로 줌



- **RequestSignUpData.kt**, **ResponseSignUpData.kt**

  - RequestSignUpData

    ```kotlin
    data class RequestSignUpData(
        @SerializedName("email")
        val id : String,
        val name : String,
        val password : String
    )
    ```

  - ResponseSignUpData

    ```kotlin
    data class ResponseSignUpData(
        val status : Int,
        val success : Boolean,
        val message : String,
        val data : Data
    ) {
        data class Data(
            val id : Int,
            val name : String,
            val password : String,
            val email : String
        )
    }
    ```

  - Json 객체의 키 값과 타입을 각각 데이터 클래스의 변수명과 타입에 일치시켜줌

    - ResponseData ▶️ Retrofit 구현체에 추가되는 gsonConverter가 gson 데이터를 방금 만든 ResponseData로 변환시켜 줌
    - RequestData ▶️ gson 데이터로 변환시켜 서버에 전달



- **LoginService.kt**

  ```kotlin
  interface LoginService {
      @Headers("Content-Type:application/json")
      @POST("user/login")
      fun postLogin(
          @Body body : RequestLoginData
      ) : Call<ResponseLoginData>
  
      @Headers("Content-Type:application/json")
      @POST("user/signup")
      fun postSignUp(
          @Body body : RequestSignUpData
      ) : Call<ResponseSignUpData>
  }
  ```

  - `@Headers` ▶️ 헤더값 넣어줌
  - `@POST` ▶️ HTTP 메소드를 설정해주고 API의 URI를 작성
  - `@Body` ▶️ RequestBody 데이터를 넣어줌



- **SignUpServiceCreator.kt**

  ```kotlin
  object SignUpServiceCreator {
      private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
  
      private val retrofit: Retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
  
      val signUpService: LoginService = retrofit.create(LoginService::class.java)
  }
  ```

  - `BASE_URL` : 메인 서버 도메인
  - Retrofit 객체 생성
    - Retrofit.Builder() : 레트로핏 빌더 생성 (생성자 호출)
    - baseUrl(BASE_URL) : 빌더 객체의 baseUrl 호출. 서버의 메인 URL 전달
    - addConverterFactory(GsonConverterFactory.create()) : gson 컨버터 연동
    - build() : Retrofit 객체 반환
  - 인터페이스 객체를 create에 넘겨 실제 구현체 생성



- **SignUpActivity.kt**

  ```kotlin
  private fun initNetwork() {
          val requestSignUpData = RequestSignUpData(
              id = binding.etId.text.toString(),
              name = binding.etName.text.toString(),
              password = binding.etPassword.text.toString()
          )
  
          val call: Call<ResponseSignUpData> =
              SignUpServiceCreator.signUpService.postSignUp(requestSignUpData)
  
          call.enqueue(object : Callback<ResponseSignUpData> {
              override fun onResponse(
                  call: Call<ResponseSignUpData>,
                  response: Response<ResponseSignUpData>
              ) {
                  if (response.isSuccessful) {
                      val intent = Intent()
                      intent.putExtra("id", requestSignUpData.id)
                      intent.putExtra("pw", requestSignUpData.password)
                      setResult(Activity.RESULT_OK, intent)
                      finish()
                  } else {
                      Toast.makeText(this@SignUpActivity, "회원 가입에 실패하였습니다", Toast.LENGTH_SHORT)
                          .show()
                  }
              }
  
              override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                  Log.e("NetworkTest", "error:$t")
              }
          })
      }
  ```

  - 회원가입 버튼 클릭 시 `initNetwork()`
  - `requestSignUpData` : 서버에 요청을 보내기 위해 생성
  - `call.enque` ▶️ 실제 서버 통신을 비동기적으로 요청
  - `response.isSuccessful` ▶️ Status Code가 200~300일 때 true를 반환
  - `onFailure` ▶️ `response.isSuccessful`이 false이거나 body()에 값이 없을 경우 에러 처리



### 3. 배운 내용

- RequestedLoginData.kt 에서 

  ```kotlin
  @SerializedName("email")
  ```

  부분이 `unresolved reference serializedname` 로 계속 오류가 났다. build.gradle 에서 `implementation 'com.google.code.gson:gson:2.8.6'` 을 `implementation 'com.google.code.gson:gson:2.8.8'`로 고쳐서 해결했다.



- **gson** : Java에서 Json을 파싱하고, 생성하기 위해 사용되는 구글에서 개발한 오픈소스

  **retrofit2** : HTTP API를 자바 인터페이스 형태로 사용하는 라이브러리



- Postman

  : API 개발을 보다 빠르고 쉽게 구현할 수 있도록 도와주며, 개발된 API를 테스트하여 문서화 또는 공유할 수 있도록 도와주는 플랫폼

  - 공유된 API 테스트
  - 서버 통신 작업에 대한 실수를 방지



- Response 코드

  - 1xx (정보) : 요청을 받았으며 프로세스를 계속함
  - 2xx (성공) : 요청을 성공적으로 받았으며 인식했고 수용함
  - 3xx (리다이렉션) : 요청 완료를 위해 추가 작업 조치가 필요함
  - 4xx (클라이언트 오류) : 요청의 문법이 잘못되었거나 요청을 처리할 수 없음
  - 5xx (서버 오류) : 서버가 명백히 유효한 요청에 대해 충족을 실패했음



- Json의 키 값과 데이터 클래스의 변수명이 같은 경우에는 @SerializedName을 사용하지 않아도 자동으로 맵핑되어 변환된다. 하지만 Jsom의 키 값(email)과 클래스의 변수명(id)이 다른 경우에는 @SerializedName으로 이름표를 붙여야 맵핑이 된다.
