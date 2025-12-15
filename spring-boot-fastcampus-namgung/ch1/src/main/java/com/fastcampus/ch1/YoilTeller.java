package com.fastcampus.ch1;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@Controller
public class YoilTeller {

  @RequestMapping("/yoil")
  public void main(int year, int month, int day, Model model) throws IOException {

    // 2. 처리
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(year, month - 1, day);

    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

    model.addAttribute("year", year);
    model.addAttribute("month", month);
    model.addAttribute("day", day);
    model.addAttribute("yoil", yoil);


//    return "yoil"; // yoil.html - view의 이름을 반환



    // 3. 출력
  }
}
