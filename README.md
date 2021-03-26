# 목소리로 알려 주는 스마트 복약 관리 서비스 'Pillgnal' 💊

### 구성원
**FE** 김재성 / 윤혜민 / 조성국
<br>
**BE** 김재승 / 엄재웅

### 기술 스택
**FE** Vue.js, Vuetify, Bootstrap
<br>
**BE** SpringBoot, JPA, Docker, Gradle, MariaDB, nginx
<br>
**ETC** Jira, Mattermost, Discord, GitLab
<br>

### 설치
    build.gradle -> dependencies
    implementation group: 'com.google.cloud', name: 'google-cloud-vision', version: '1.101.1'
    implementation 'org.springframework.cloud:spring-cloud-gcp-starter-storage'

<hr>
<br>

## Pillgnal? 
> Pill (알약) + Signal(신호)를 합친 말로, 약을 먹어야할 시간과 복약정보를 알려주는 웹 서비스입니다.

<br>

## 기획배경
* 복용 시간을 까먹었을 때
* 잘못된 약을 섭취했을 때
* 부모님이 약을 잘 드시는지 궁금할 때

🤔 **불편을 개선할 수 있는 서비스가 필요하지 않을까?**

<br>

## Logo & Character
#### 필그널 main 로고
> 알약 + 신호를 잘 나타내는 메인 로고입니다.

![logo](Readme.assets/logo.png)

#### 필그널 캐릭터 '필구'
> 필구가 복약 핵심 정보를 알려줍니다.

<img src="Readme.assets/pilgu.png" alt="pilgu" style="zoom:50%;" />

<br>

## 기능설명
### 1. 처방전 스캐닝
* 약봉투, 처방전을 사진으로 찍으면 자동으로 복약정보를 저장해줍니다.

### 2. 복약 알림
* 인식한 내용을 바탕으로 날짜, 시간에 따라 약 먹을 시간에 알림을 줍니다.

### 3. 처방전 리스트
* 처방 받은 목록을 보여줍니다.
* 처방 받은 약에 대한 정보도 알 수 있습니다.

### 4. 가족 연결
* 가족과 연결하면 구성원들의 복약 상태를 확인할 수 있습니다.

### 5. 약 정보 검색
* 궁금한 약을 사진을 찍기만 하면, 약학 정보원 API를 사용해 검색해줍니다.

<br>

## 와이어프레임
![wireframe](Readme.assets/wireframe.gif)
<br>

https://www.figma.com/proto/7ax4XD2rl2tCJ1rXzvgtbX/pillgnal-prototype-ver.1?node-id=1%3A189&scaling=min-zoom
<br>

## 진행 상황
* 기능 정의
* 프로토타입 완성
* 알약 구별 인공지능 기능 완성
* 프론트엔드 UI 개발 진행
* 백엔드 DB설계 및 API개발 진행

#### 기능구현
> OCR로 약 구분 표시(약에 적힌 글씨 ) 추출 가능

<img src="https://cdn.discordapp.com/attachments/813209640154038274/821968866354921532/KakaoTalk_20210318_102436639.jpg" alt="img" style="zoom:15%;" />

<img src="https://cdn.discordapp.com/attachments/813209640154038274/821992600810029096/erye.PNG" alt="img" style="zoom:50%;" />



:file_folder:**DataSet**

- shape: 23986 x 29
- train_set : valid_set : test_set = 8 : 1 : 1

**모양별로** **11**개의 데이터 분류

```markdown
 "0": "circle",   # "0": "원형"
 "1": "hexagon",  # "1": "육각형"
 "2": "octagon",  # "2": "팔각형"
 "3": "oval", # "3": "타원형"
 "4": "pentagon",  # "4": "오각형"
 "5": "rectangle",  # "5": "직사각형"
 "6": "rhombus",  # "6": "마름모형"
 "7": "semicircular",  # "7": "반원형"
 "8": "square",  # "8": "정사각형"
 "9": "triangle"  # "9": "삼각형“
 “10”: “other”  # “10”: “기타”
```

Keras의 ImageDataGenerator클래스 사용

분류별로 약 1000개씩 맞춤



:computer:**사용 모델**: **CNN**

:warning:**이슈 발생**

![image-20210326105407002](README.assets/image-20210326105407002.png)

- “기타“ 이미지는 정말 생긴게 제각각이다.
- 특정 "모양"으로 분류하기 어려워 분류기 성능을 위해 데이터 제거



:outbox_tray:**학습 결과**

- 정확도: **98%**+

![image-20210326105547607](README.assets/image-20210326105547607.png)

![image-20210326105552889](README.assets/image-20210326105552889.png)

<br>

## 앞으로의 계획
* 약의 모양 중 "기타(Other)" 처리 방향  
* 처방전 데이터 인식

#### 아쉬운점
- 현재 모델로는 약의 모양 중 "기타(Other)" 파트에 대한 구별이 어려움


