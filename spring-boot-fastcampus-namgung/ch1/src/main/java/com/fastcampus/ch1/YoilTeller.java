package com.fastcampus.ch1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class YoilTeller {

    @RequestMapping("/yoil")
    public String main(HttpServletRequest request){
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        System.out.println( "year = "+year );
        System.out.println( "month = "+month );
        System.out.println( "day = "+day );

        return "yoil";
    }
}
