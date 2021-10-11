# 필독(참고) 노트

## 화면

- 만약 페이징 로드중 오류가 발생했다면 
  - <img src="https://user-images.githubusercontent.com/40740128/136690315-04c9cac8-a37e-4a1e-91fd-ffd82f7baeca.png" alt="image" width="33%" />

- 만약 프로필 조회중 오류가 발생했다면
  - <img src="https://user-images.githubusercontent.com/40740128/136690336-e52b3e86-365b-4b58-873a-676fe6b82827.png" width="33%" />

- 만약 프로필 조회시, 레포지토리와 이벤트 목록이 비었다면
  - <img src="https://user-images.githubusercontent.com/40740128/136689466-0b4b1cfd-0e68-4be7-a9d6-4d78c10dbf41.png" width="33%" />

## 정보

- 프로필 조회시, 무한 로딩이 걸린다면
  - 해당 이슈: [#2](https://github.com/jisungbin/AndroidModernArchitectureSample/issues/2)
- 프로필 조회시, 레포지토리와 이벤트 목록이 아무것도 표시되지 않는다면
  - ~~5초 이내로 표시 됩니다. 잠시만 기다려 보세요.~~
  - ~~몇 초가 지나도 로드되지 않는다면 앱을 재시작 해주세요.~~
  - 버그 수정 완료: [#01b7ff6](https://github.com/jisungbin/AndroidModernArchitectureSample/commit/01b7ff6ea4fb0ce76d861f9445807efc1e192dc6)
- 페이징(무한 스크롤)이 작동하지 않는다면
  - 더 이상 불러올 정보가 없어 페이징이 종료된 거일 수도 있습니다.
  - 불러올 정보들이 남아있지만 작동하지 않는다면, 스크롤을 위로 올렸다가 다시 아래로 내려보세요.
  - 스크롤을 다시 해봐도 작동하지 않는다면 앱을 재시작 해주세요.
- Built at 토스트가 뜨는 이유
  - 가끔씩 수정된 코드로 빌드가 안되는 경우가 있어서, 실행시 빌드된 시간을 확인하기 위해 추가하였습니다.

## 팁

- 레포지토리 이름을 클릭하면 해당 레포지토리로 브라우저가 열립니다.
