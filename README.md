#[위메프] 코딩과제

### 프로그래밍 환경
- java 1.8
- spring boot 2.5.4
- junit5

### 요구사항 정리
- 사용자의 http request 받을 수 있다. request body에 포함되는 항목은 다음과 같다.
  - url
  - type
    - html 태그 제외
    - Text 전체
  - 출력 단위 묶음  
- 사용자 입력으로 받은 URL로 페이지를 가져 올 수 있다.
- 가져온 페이지르 type에 따라 파싱한다
  - HTML 태그 제외
    - html 태그는 제외하고 text 값만을 출력
  - Text 전체
    - html 태그 포함한 전체 text 출력
- 파싱된 문자열에 대문자, 소문자, 숫자를 제외한 모든 문자를 필터링 할 수 있다.
- 숫자는 숫자대로, 문자는 문자대로 분리 하여 오름차순으로 정렬 할 수 있다.
- 사용자 입력으로 받은 출력단위 묶음으로 숫자, 문자를 묶을 수 있다.
- 영어,숫자,영어,숫자... 순서로 입력을 재 정렬 할 수 있다.
- 몫과 나머지를 출력할 수 있다.
  - 몫
    - 출력 묶음 단위 내값(구분자,)
  - 나머지
    - 출력 묶음 단위 외값
- 사용자의 http request 대한 reponse을 보낼 수 있다. response body에 포함되는 항목은 다음과 같다.
  - 몫
  - 나머지