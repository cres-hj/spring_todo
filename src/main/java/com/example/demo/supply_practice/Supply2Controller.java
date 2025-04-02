package com.example.demo.supply_practice;

import jakarta.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.time.*;
import java.util.*;

@Controller
public class Supply2Controller {
  private List<Supply2> supplies = new ArrayList<>();
  private int sno = 3;

  @PostConstruct
  public void init() {
    supplies.add(new Supply2(1, "밀떡볶이", LocalDate.of(2025, 4, 1), 7));
    supplies.add(new Supply2(2, "쌀떡볶이", LocalDate.of(2025, 4, 1), 7));
  }

  // C
  @PostMapping("/supply2/add")
  public ModelAndView add(@ModelAttribute Supply2 supply) {
    supply.setSno(sno++);
    supplies.add(supply);
    return new ModelAndView("redirect:/supply2/list");
  }


  // R
  @GetMapping("/supply2/list")
  public ModelAndView list() {
    return new ModelAndView("supply2/list").addObject("supplies", supplies);
  }

  // U
  @PostMapping("/supply2/increase")
  public ModelAndView increase(@RequestParam int sno) {
    for(Supply2 supply:supplies) {
      if(supply.getSno()==sno) {
        supply.setQuantity(supply.getQuantity()+1);
      }
    }
    return new ModelAndView("redirect:/supply2/list");
  }

  @PostMapping("/supply2/decrease")
  public ModelAndView decrease(@RequestParam int sno) {
    for(Supply2 supply:supplies) {
      if(supply.getSno()==sno && supply.getQuantity()>=1) {
        supply.setQuantity(supply.getQuantity()-1);
      }
    }
    return new ModelAndView("redirect:/supply2/list");
  }

  // D
  @PostMapping("/supply2/delete")
  public ModelAndView delete(@RequestParam int sno) {
    for(int i=supplies.size()-1; i>=0; i--) {
      if(supplies.get(i).getSno()==sno) {
        supplies.remove(supplies.get(i));
      }
    }
    return new ModelAndView("redirect:/supply2/list");
  }
}
