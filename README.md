# CoffeeShop
# 공부용 커피숍 프로젝트
커피숍 시스템에 필요한 기능들을 구현해 보았습니다.

1. ### **서비스 설명**

   - 커피 주문에 필요한 메뉴를 구성하고 조회가 가능합니다.
   - 커피 주문은 포인트로 가능합니다.
   - 커피 주문내역을 통해 인기있는 메뉴를 추천합니다.

2. ### 구현 API 목록

   - 커피 메뉴 목록 조회 API
   - 포인트 충전하기 API
   - 커피 주문, 결제하기 API
   - 인기메뉴 목록 조회 API

3. ### API 설명

   1. 커피 메뉴 목록 조회 API
      - 커피 정보 (메뉴 ID,이름 ,가격) 을 조회하는 API
   2. 포인트 충전하기 API
      - 결제는 포인트로만 가능하며, 포인트를 충전하는 API
      - 사용자 id, 충전금액을 입력 받아 포인트를 충전합니다.
   3. 커피 주문/결제 하기 API
      - 사용자 id, 메뉴 id 를 입력받아 주문을 하고 결제를 진행
      - 결제는 포인트로만 가능하며 충전한 포인트에서 주문금액을 차감
   4. 인기메뉴 목록 조회 API
      - 최근 7일간 인기있는 메뉴 3개를 조회하는 API

# **ERD**
![ERD](https://user-images.githubusercontent.com/41957723/216234761-9d92b7b3-97a7-4f94-b8ac-33c66976c478.png)

### **개선내용**



1. **중복쿼리 제거**

   - orderService에서 주문받은 커피를 처리하는 로직에서 dto에 수량과 커피 id를 받아오고 새로운 Order와 수량,커피종류,총 가격이 담겨있는 OrderDetail을 생성하기 전에 Dto로부터 받은 id와 수량을 가지고 총 금액이 얼마인지 미리 계산하게끔 설계하였다. 

     ```java
     private Long getTotalPrice(OrderCoffeeDto dto) {
         long totalPrice = 0L;
         for (int i = 0; i < dto.getOrderCoffeeDetailDto().size(); i++) {
             Long coffeeId =dto.getOrderCoffeeDetailDto().get(i).getCoffeeId();
             
             Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow();
             
             int quantity = dto.getOrderCoffeeDetailDto().get(i).getQuantity();
             int price = coffee.getPrice();
     
             totalPrice += (long)price*quantity;
         }
         return totalPrice;
     }
     ```

     위와같은 메소드를 초기에 만들었는데 주문받은 커피들이 Order에 등록될 때에도 동일한 쿼리가 나가고 있는것을 발견햇고 비록 성능에 큰 영향을 미치지 않는 수준이라 하여도 동일한 쿼리를 여러번 날릴 필요는 없다고 판단, 총 주문금액을 Order에 OrderDetail을 관계맺어줄 때 마다 합산하도록 변경하여 동일한 쿼리가 나가는 것을 방지하였다.
