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

## 기획배경
* 

<br>

## Logo & Character


## 기능설명

<br>

## 와이어프레임
![wireframe](Readme.assets/wireframe.gif)
<br>

https://www.figma.com/proto/7ax4XD2rl2tCJ1rXzvgtbX/pillgnal-prototype-ver.1?node-id=1%3A189&scaling=min-zoom
<br>

## 진행 상황
* 기능 정의
* 프로토타입 완성

#### 기능구현
> OCR로 약 구분 표시(약에 적힌 글씨 ) 추출 가능

![img](https://cdn.discordapp.com/attachments/813209640154038274/821968866354921532/KakaoTalk_20210318_102436639.jpg)

![img](https://cdn.discordapp.com/attachments/813209640154038274/821992600810029096/erye.PNG)

<br>

## 앞으로의 계획
* 약의 모양 (사각형/ 원형 /타원형 등) 인식  
* 약의 색상 인식 (하양, 노랑, 주황 등)  
* 약의 제형 (정제류 / 캡슐형)

#### 아쉬운점
- 색상인식 까지는 어렵지 않을 것으로 보이나, 약의 모양을 인식하기 위한 충분한 Dataset을 찾아야 정확한 모델링이 가능할 것으로 보임
  - 현재 Kaggle등의 사이트에서 비슷한 데이터를 찾았으나 다소 아쉬움.


