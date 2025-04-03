package com.example.demo.supply;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

public class Supply3Controller {
  private List<Supply> supplies = new ArrayList<>();
  // 서버 재시작하거나 컴 껐다키면 내용 날아가 but 메모리에서 돌아가니까 속도가 빠름
  //  -> 속도가 중요한 경우에는 이렇게 리스트 만들어 쓰고(=인메모리db) 아니면 db써
  private int sno = 1;

  // Create
  @PostMapping("/supply3/create")
  public ModelAndView create(@ModelAttribute Supply supply) {
    supply.setSno(sno++);
    supplies.add(supply);
    // 작업 끝났으면 새 작업하러 이동해야지
    return new ModelAndView("redirect:/supply3/list");
  }

  // Read
  @GetMapping("/supply3/list")
  public ModelAndView readAll() {
    // 만약 하나만 읽는다하면 readOne(@RequestParam int sno) 이런식으로 가야해
    // 지금은 전체 다 읽어올거라 뭐 받아올 필요x
    return new ModelAndView("supply/list").addObject("supplies", supplies);
  }

  // Update
  public ModelAndView update(@RequestParam int sno) {
    // foreach: 리스트에서 원소를 하나씩 꺼내는 for문 (ex for(Supply s:supplies))
    //     개발자는 리스트 내 원소의 개수를 알 필요가 없음. 파이썬도 이거 있음! for s in supplies
    for(Supply s:supplies) {
      // 객체는 .equals로 비교해야해
      if(s.getSno().equals(sno)) {
        s.setSno(s.getSno()+1);
      }
      break;
    }
    return new ModelAndView("redirect:/supply3/list");
  }

  public ModelAndView delete(@RequestParam int sno) {
    for(Supply s:supplies) {
      // 객체는 .equals로 비교해야해
      if(s.getSno().equals(sno)) {
        supplies.remove(s);
      }
      break;
    }
    return new ModelAndView("redirect:/supply3/list");
  }
}
