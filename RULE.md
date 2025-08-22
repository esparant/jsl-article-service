# 코드 작성 규칙
### 1. 모듈을 작성시 클래스 상단에 uthor, date, description, version, history 를 표기한다.
- Javadoc 기능을 활용한다.
- 예시
~~~java
/**
 * @author Yeongbok Tak
 * @modifier Gyujin Jeong   
 * @date 2025-08-19
 * @version 1.2
 * @description 사용자 정보를 처리하는 클래스
 * 
 * @history
 *   v1.0 - 2025-08-10: 최초 작성 (Yeongbok Tak)
 *   v1.1 - 2025-08-15: 이메일 검증 기능 추가 (Yeongbok Tak)
 *   v1.2 - 2025-08-19: 성별 필드 추가 (Gyujin Jeong)
 */
public class User {
~~~
### 2. 조건문은 한줄로 끝나도 항상 {} 를 추가한다.  
- 예시
~~~ java
if (condition) doSomething(); // NG

if (condition) { // OK
  doSomething();
}
~~~
### 3. 네이밍 규칙은 다음을 따른다.
- 예시
  -  클래스명: UpperCamelCase → UserManager, OrderService
  -  메서드/변수명: lowerCamelCase → getUserName(), userEmail
  - 상수: UPPER_SNAKE_CASE → MAX_SIZE, DEFAULT_TIMEOUT
### 4. 불필요한 코드는 제거한다.
- 사용하지 않는 import, 메서드, 변수는 삭제한다.
### 5. Javadoc 은 적절한 곳에 활용한다.
- 복잡한 메서드, 예외처리 등
- 예시
~~~java
/**
 * 사용자 이름을 반환합니다.
 * @return 사용자 이름
 */
public String getName() {
    return name;
}
~~~
# Git branch 전략 
### 그게 뭐죠?
- [링크](https://youtu.be/EV3FZ3cWBp8)
  - 한번씩 봐주세요.
### 우리의 전략
- 저희는 GitHub flow 랑 비슷한 방식으로 할건데, 약간 고쳐서 하도록 하겠습니다.
- GitHub flow 를 완전히 구현하기 위해서는 Github Action 같은 걸 활용해서 Merge 될때마다 자동화 어쩌구 하는 게 있지만
- 그런건 이용하지 않고 각 기능마다 원격의 Branch 를 만들고 그 안에서 기능 구현하는 식으로 하겠습니다.
- 원격의 Branch 를 만드는 이유는 다른 사람도 보기 위해서입니다. Local Branch 방식은 자기의 컴퓨터에서만 확인 가능하기 때문에 다른사람이 학인을 못합니다.
- 그러한 이유로 각 Branch 에 기능을 만들고 **테스트** 까지 완전히 완료후 Pull Request 를 작성하면 Main Branch 에 합병하는 방식으로 하겠습니다.
- 그리고 Pull Request 를 통해 팀원들이 확인한 후 문제점이 발생하면 수정하고 PR(Pull Reqeust) 를 다시 업로드 하는 방식으로 하겠습니다.
### Pull Request 작성법
- 제목: 간결하게 작성
- 본문: 목적, 내용, 테스트방법, 참고사항 등을 구체적으로 적어줍니다.
### Pull Request 주의사항
- 너무 많은 내용이 담겨있는 것을 올리면 리뷰하기 힘들어집니다.
- 그것을 방지하기 위해 우선 기능 브랜치를 만들고 그 기능 브랜치에 하위 브랜치를 만들어 조금씩 수정하는 것을 추천드립니다.
- 무슨 내용인지 잘 모르시다면 실제로 개발 로직 작성이 들어가면 예시를 참고할 수 있도록 하겠습니다.
