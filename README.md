### 뿌리기 생성
- throwing, throwing_thread 테이블을 사용하였습니다(1:N)
- 뿌리기 생성 시 throwing 테이블에 저장되고, 인원수 별로 랜덤하게 생성된 금액이 throwing-thread 테이블에 저장됩니다
- 금액은 Random 클래스를 통해 랜덤한 숫자를 생성합니다
    - 나눠야 할 인원수를 제외한 금액 범위 내에서 생성하였으므로, 0원으로 할당되는 경우는 없습니다

### 뿌리기 받기
- token 으로 throwing 을 찾고, 자식 throwing_thread 중 1건을 찾아 사용자에게 할당합니다
    - 할당한 사용자의 id 를 throwing_thread 에 기록합니다
- JPA optimisitic lock 을 사용하여 동시에 같은 thread 를 잡았을 시 다시 위 로직을 다시 시도합니다

### 토큰 생성
- 000 ~ zzz 사이 랜덤한 문자를 생성합니다
- 생성 후 대상 테이블에서 조회하여 이미 사용중인지 체크하고, 이미 사용중일 경우 다시 생성합니다

### 기타
- Spring Boot, JPA를 사용하였습니다
- DB는 H2를 사용하였습니다
- OAS 스펙으로 api 문서를 작성하고, 이를 기반으로 controller 코드를 generate 하였습니다
- 인수 테스트, 단위 테스트를 작성하였습니다(커버리지 97%)