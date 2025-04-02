package com.example.demo.todo;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class TodoController {
  private List<Todo> todos = new ArrayList<>();
  private int tno = 1;

  // C - GET, POST
  @GetMapping("/todo/write")
  public void write() {
    // 지금 이 땐 별도로 할 게 없고 todo/write로 들어오면 html 보여주는거라 void 쓰면 됨
    // public ModelAndView로 해도 되긴 한데 사용자 입력 주소랑 html 위치랑 다르면 저렇게 하는데 지금은 그냥 주소 같으니까 void


  }

  // 사용자가 요청 객체에 담긴 request
  // <input name="title">이 요청 객체에 담겨 백엔드로 저장되면 같은 이름의 파라미터에 담는다
  // @RequestParam: 요청 객체이서 이름이 같은 데이터를 추출해 파라미터에 담아라 (생략가능) + 변환해서 넣어줌(변수 타입이 int면 int로, String이면 String으로 ...)
  //                Q. 스프링은 어떤 데이터라도 파라미터로 변환할 수 있을까?
  //                   만약에 <input type='date> 입력하면 서버로 2020-11-20으로 넘어가 -> 이걸 2020년 11월 20일 객체로 변환이 될까?
  //                   A. 불가능!
  @PostMapping("/todo/write")  // 둘이 굳이 주소 다르게 할 필요x
  public ModelAndView write(@RequestParam String title) {  // 얜 보여주는게 아니라서 ModelAndView
    Todo todo = new Todo(tno++, title);
    todos.add(todo);
    return new ModelAndView("redirect:/todo/list");
    // redirect: <-까지는 문법이고 redirect는 주소라서 /로 시작, html은 /로 시작하면 안됨 파이썬도 동일!
  }

  // R - 목록
  @GetMapping("/todo/list")
  public ModelAndView list() {
    return new ModelAndView("todo/list").addObject("todos", todos);
    // addObject 써서 출력할 값 가져갈거라 모델앤뷰 헷갈리면 걍 ModelAndView 쓰면 됨
  }

  @PostMapping("/todo/finish")
  public ModelAndView finish(@RequestParam int tno) {
    for(Todo todo:todos) {
      if(todo.getTno()==tno) {
        todo.setFinish(true);
        break;
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }
  @PostMapping("/todo/delete")
  public ModelAndView delete(@RequestParam int tno) {
    // 자바에서 for문 돌릴 때 0번째 원소부터 차례대로 찾으면서 삭제할 경우, 접근하지 않고 통과하는 원소가 있다
    // 인덱스: 0 1 2 3
    // 값     A B C D
    // B를 찾아서 ㅅ지울 경우-> b가 지워지는 순간에 c가 첫번째, D가 둡너째 원소가 된다
    //                      인덱스는 1에서 2로 증가 ->  C를 건너 뛰고 D로 접근 -> 자바가 용납x
    for(int i=todos.size()-1; i>=0; i--) {
      if(todos.get(i).getTno()==tno) {
        todos.remove(todos.get(i));
        // 지금 번호(tno)로 삭제하지 않고 인덱스로 삭제해
        break;
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }
}
