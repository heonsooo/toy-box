package com.fastcampus.ch1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

@RestController
public class YoilTeller {

  @RequestMapping("/yoil")
  public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // 1. 입력
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");

    int yyyy = Integer.parseInt(year);
    int mm = Integer.parseInt(month);
    int dd = Integer.parseInt(day);


    // 2. 처리
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(yyyy, mm - 1, dd);

    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    char yoil = "일월화수목금토".charAt(dayOfWeek - 1);


    // 3. 출력
    response.setCharacterEncoding("utf-8");
    PrintWriter out = response.getWriter();

    out.println("<html >");
    out.println("<head>");
    out.println("</head>");
    out.println("<body>");
    out.println("year = " + year);
    out.println("month = " + month);
    out.println("day = " + day);
    out.println("yoil = " + yoil);
//        out.println( "오늘 날짜 : "+cal );
    out.println("</body>");
    out.println("</html>");

//        return "yoil";
  }
}
