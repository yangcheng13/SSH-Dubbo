package com.apex.holiday.trip.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.apex.holiday.api.TestService;
import com.apex.holiday.dto.HolidayTestDto;

/**
 * @desc: holiday-consumer-trip
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午3:50:01
 * @version: v1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private TestService holidayTestService;

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 上午11:55:58
     * @return ModelAndView
     */
    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        List<HolidayTestDto> dtos = holidayTestService.listCity();
        mv.addObject("cityData", dtos);
        return mv;
    }

}
