package com.example.demo.todo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
public class Todo {
  private int tno;  //  백에서 걸어줄거야
  private String title;  // 사용자한테서 받아올거야
  private final LocalDate regDate = LocalDate.now(); // 이렇게 final 걸면 생성자에서 빠짐
@Setter
  private boolean finish = false;
  public Todo(int tno, String title) {
    this.tno = tno;
    this.title = title;
  }
}


// 이 개체는 스프링빈 아님! 사람이 만드러야해
// 값을 저장하는 클래스야 => 스프링빈 아님