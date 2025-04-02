package com.example.demo.supply;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

// 비품번호, 입고일, 이름, 개수
// 기본값 줄 수 있는게 없어 => 다 입력 => allargs쓰자

// 사용자로부터 값을 입력받는 객체: Command 객체(☆입력받은 값을 그냥 저장하는 객체. Command 객체가 VO 객체의 한 종류(VO: 그냥 값을 저장하는 객체))
// 스프링에서는 Command 객체는 반드시 기본생성자와 Setter가 있어야해
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
  private Integer sno;
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate regDate;
  private Integer quantity;
  // Controller부분에서 @ModelAttribute 써서 int 다 Integer로 바꿔줌(앵간하면 통일)
}
