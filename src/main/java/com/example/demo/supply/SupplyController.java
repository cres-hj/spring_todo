package com.example.demo.supply;

import jakarta.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.time.*;
import java.util.*;

@Controller
public class SupplyController {
  private List<Supply> supplies = new ArrayList<>();
  private int sno = 4;

  @PostConstruct
  public void init() {
    supplies.add(new Supply(1, "펩시콜라", LocalDate.of(2025, 3, 25), 7));
    supplies.add(new Supply(2, "막걸리", LocalDate.of(2025, 3, 26), 7));
    supplies.add(new Supply(3, "펩시콜라", LocalDate.of(2025, 3, 27), 7));
  }

  // 비품 목록을 출력하는 메소드와 html 작성
  // html에는 번호, 제품명, 입고일, 개수, 증가버튼, 감소버튼 + 부트스트랩

  // C 제품추가
  @PostMapping("/supply/add")
  // public ModelAndView add(@RequestParam String name, LocalDate regDate, int quantity)
  // @ModelAttribute: 사용자 입력을 담은 객체를 생성 (입력되지 않은건 null로 받음)
  //    Supply 객체로 사용자 입력을 받아오는데 모든 값을 사용자가 지금 입력하는게 아니야 => 입력하지 않은 필드는(=sno) null이 들어가
  //    sno는 사용자가 입력하지 않아서 지금 여기 null이 들어갈건데 int는 null 못받아 -> sno는 Integer로 해줘야해)
  // @ModelAttribute는 기본 생성자로 객체를 생성한 다음, setter로 값을 집어넣음
  public ModelAndView add(@ModelAttribute Supply supply) {
    supply.setSno(sno++);
    supplies.add(supply);
    return new ModelAndView("redirect:/supply/list");
  }

  // R
  @GetMapping("/supply/list")
  public ModelAndView list() {
    return new ModelAndView("supply/list").addObject("supplies", supplies);
  }
  
  // U: 개수변경
  @PostMapping("/supply/increase")
  public ModelAndView increase(@RequestParam int sno) {
    for(Supply supply:supplies) {
      if(supply.getSno()==sno) {
        supply.setQuantity(supply.getQuantity() + 1);
      }
    }
    return new ModelAndView("redirect:/supply/list");
  }

  @PostMapping("/supply/decrease")
  public ModelAndView decrease(@RequestParam int sno) {
    for(Supply supply:supplies) {
      if(supply.getSno()==sno && supply.getQuantity() >= 1) {
        supply.setQuantity(supply.getQuantity() - 1);
      }
    }
    return new ModelAndView("redirect:/supply/list");
  }

  // D: 삭제
  @PostMapping("/supply/delete")
  public ModelAndView delete(@RequestParam int sno) {
    for(int i=supplies.size()-1; i>=0; i--) {
      if(supplies.get(i).getSno() == sno) {
        supplies.remove(supplies.get(i));
        break;
      }
    }
    return new ModelAndView("redirect:/supply/list");
  }

}
