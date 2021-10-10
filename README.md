# AndroidModernArchitectureSample [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

> Github user searcher

---

# Github Users [한국어](https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/README-kr.md)

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

[Google Play Store](https://play.google.com/store/apps/details?id=io.github.jisungbin.githubusers) or [Github Release](https://github.com/jisungbin/AndroidModernArchitectureSample/releases/tag/1.0.0) available for download. (Google Play Store may not be displayed as it is under review)

---

# License

Github Users © 2021 Ji Sungbin. all rights reserved.

Github Users are licensed under [Apache 2.0](https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE).



# Happy Coding :)
