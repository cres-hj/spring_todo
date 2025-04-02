package com.example.demo.todo;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class TodoController2 {
  private List<Todo> todos = new ArrayList<>();
  private int tno = 1;

  // R
  @GetMapping()
  // 여기 주소 "/todo/list"로 똑같이 걸면 안됨
  // 이전 파일도 얘도 컨트롤러야 -> 스프링이 모두 읽어 -> 같은 주소에 2개의 기능이 같이 있어 -> 다운~
  public ModelAndView list() {
    return new ModelAndView("todo/list").addObject("todos", todos);
  }

  // C
  public ModelAndView write(@RequestParam String title) {
    Todo newTodo = new Todo(tno++, title);
    todos.add(newTodo);
    return new ModelAndView("redirect:/todo/list");
  }

  // 변경
  public ModelAndView finish(@RequestParam int tno) {
    // 변경/삭제는 무조건 번호 가져와야해
    for(Todo todo:todos) {
      if(todo.getTno()==tno) {
        todo.setFinish(true);
        break;
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }

  public ModelAndView delete(@RequestParam int tno) {
    for(int i=todos.size()-1; i>=0; i--) {
      if(todos.get(i).getTno()==tno) {
        todos.remove(i);
        break;
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }

}
