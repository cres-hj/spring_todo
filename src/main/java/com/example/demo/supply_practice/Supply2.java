package com.example.demo.supply_practice;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supply2 {
  private Integer sno;
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate regDate;
  private Integer quantity;
}
