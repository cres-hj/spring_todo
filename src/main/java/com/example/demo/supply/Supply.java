package com.example.demo.supply;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

// 비품번호, 입고일, 이름, 개수
// 기본값 줄 수 있는게 없어 => 다 입력 => allargs쓰자

// 사용자로부터 값을 입력받는 객체: Command 객체(☆입력받은 값을 그냥 저장하는 객체. Command 객체가 VO 객체의 한 종류(VO: 그냥 값을 저장하는 객체))
// Command객체에는 사용자가 입력한 값이 들어있어 <- @ModelAttribute 써서 클래스로 받으면 이거야
// @RequestParam: 값을 하나씩 받는거
// @ModelAttribute: 클래스 써서 가져오는거
//    스프링의 모든 기능을 사용하려면 이게 안전해. 위에건 기능이 약간 제한되어있다고 보면 됨
//    근데 이거 쓰려면 무조건 클래스 만들어야해서 값이 막 하나인데 클래스 만들기 좀 그럴수도 있지
//    -> 본인 나름의 규칙 만들어서 써 ex) 값 3개 이상 받아야하면 이거쓰고 그 이하면 @RequestParam 쓰기
// 스프링에서는 Command 객체는 반드시 기본생성자와 Setter가 있어야해
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
  // 번호: supply 객체들을 구별(기본키. 예를들어 주소, 주민번호...)
  //      기본키값을 주면 객체가 1개만 나온다
  private Integer sno;
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate regDate;
  private Integer quantity;
  // Controller부분에서 @ModelAttribute 써서 int 다 Integer로 바꿔줌(앵간하면 통일)
}
