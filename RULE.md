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
### 2. if 문은 한줄로 끝나도 항상 {} 를 추가한다.  
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
### 4. 들여쓰기는 스페이스 4칸으로 설정한다.
### 5. 불필요한 코드는 제거한다.
- 사용하지 않는 import, 메서드, 변수는 삭제한다.
### 6. Javadoc 은 적절한 곳에 활용한다.
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
