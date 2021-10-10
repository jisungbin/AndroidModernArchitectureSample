# AndroidModernArchitectureSample [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

> 깃허브 유저 검색기

---

# Github Users

깃허브 유저를 검색하고 해당 유저의 프로필과 레포지토리, 그리고 최근 이벤트를 보여주는 간단한 샘플 입니다.

Github Rest API V3, Flow+Coroutines, Jetpack Compose, Room, Paging3, Dagger-Hilt, 그리고 MVI Pattern(framework: [Orbit](https://github.com/orbit-mvi/orbit-mvi))이 사용되었습니다.



## Layer

![image](https://user-images.githubusercontent.com/40740128/136686589-8815d1af-75f3-43c3-a75b-668b39511b5a.png)

- **Presentation**: Domain에서 레포지토리와 UseCase들을 가져와, 각 엑티비티별 뷰모델로 주입해 줍니다. 각 엑티비티에서는 주입 받은 뷰모델을 가져와 데이터를 조회하고 보여줍니다.
- **Data**: Domain에서 정의한 레포지토리를 구현합니다. 온라인일 경우 rest api 요청을 페이징과 함께 실행하고, 오프라인일 경우 local에서 데이터를 불러옵니다.
- **Domain**: Github rest api를 사용할 레포지토리와, UseCase들을 정의합니다.



## Mad score [[Open web]](https://madscorecard.withgoogle.com/scorecard/share/612972238/ )
![image](https://user-images.githubusercontent.com/40740128/136686854-c36f819f-8556-41ca-91a1-632209209578.png)



# Preview

<div>
<img src="https://user-images.githubusercontent.com/40740128/136687125-d20e861e-3711-4d99-ab06-87a8e025cf47.png" width="30%"/>
<img src="https://user-images.githubusercontent.com/40740128/136687129-c37fe5df-e0a8-45ff-8071-e218d6d73a3e.png" width="30%"/>
</div>

앱 메인 화면에서 앱 이름을 클릭하면 오픈소스 라이선스 리스트를 볼 수 있고, 유저 프로필 엑티비티에서 프로필 사진을 누르면 사진을 크게 볼 수 있습니다.



# Download

[구글 플레이스토어](https://play.google.com/store/apps/details?id=io.github.jisungbin.githubusers) 또는 [깃허브 릴리즈](https://github.com/jisungbin/AndroidModernArchitectureSample/releases/tag/1.0.0) 에서 다운로드가 가능합니다. (구글 플레이스토어는 심사중이라 표시되지 않을 수 있습니다)
# AndroidModernArchitectureSample [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

> Github user searcher

---

# Github Users

This is a simple sample that searches for a user on GitHub and displays the user's profile, repository, and recent events.

Github Rest API V3, Flow+Coroutines, Jetpack Compose, Room, Paging3, Dagger-Hilt, and MVI Pattern (framework: [Orbit](https://github.com/orbit-mvi/orbit-mvi)) were used.



## Layer

![image](https://user-images.githubusercontent.com/40740128/136686589-8815d1af-75f3-43c3-a75b-668b39511b5a.png)

- **Presentation**: Repositories and UseCases are imported from Domain and injected into the view model for each activity. Each activity retrieves and displays data by using the injected view model.
- **Data**: implements the repository defined by Domain. Execute rest api request with paging when online, load data from local when offline.
- **Domain**: Defines the repository and UseCases to request the Github rest api.

## Mad score [[Open web]](https://madscorecard.withgoogle.com/scorecard/share/612972238/ )

![image](https://user-images.githubusercontent.com/40740128/136686854-c36f819f-8556-41ca-91a1-632209209578.png)



# Preview

<div>
<img src="https://user-images.githubusercontent.com/40740128/136687125-d20e861e-3711-4d99-ab06-87a8e025cf47.png" width="30%"/>
<img src="https://user-images.githubusercontent.com/40740128/136687129-c37fe5df-e0a8-45ff-8071-e218d6d73a3e.png" width="30%"/>
</div>


Click the app name on the app main screen to see the list of open source licenses, and click the profile picture in the user profile activity to see a larger picture.



# Download

[Google Play Store](https://play.google.com/store/apps/details?id=io.github.jisungbin.githubusers) or [Github Release](https://github.com/jisungbin/AndroidModernArchitectureSample /releases/tag/1.0.0) available for download. (Google Play Store may not be displayed as it is under review)

---

# License

Github Users © 2021 Ji Sungbin. all rights reserved.

Github Users are licensed under [Apache 2.0](https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE).



# Happy Coding :)
---

# License

Github Users © 2021 Ji Sungbin. all rights reserved.

Github Users은 [Apache 2.0](https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE) 라이선스를 따릅니다.



# Happy Coding :)
