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
  public ModelAndView main(int year, int month, int day) throws IOException {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("yoilError");

    if(!isValid(year, month, day)){

      return mv;
    }


    // 2. 처리
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(year, month - 1, day);

    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

    mv.addObject("year", year);
    mv.addObject("month", month);
    mv.addObject("day", day);
    mv.addObject("yoil", yoil);
    mv.setViewName("yoil");

    return mv ;


//    return "yoil"; // yoil.html - view의 이름을 반환



    // 3. 출력
  }

  private boolean isValid(int year, int month, int day) {
//    year, month , day validation logic
    if(year == -1 || month == -1 || day == -1)
      return false;

    if(month < 1 || month > 12)
      return false;

    if(day < 1 || day > 31)
      return false;

    return true;
  }
}
