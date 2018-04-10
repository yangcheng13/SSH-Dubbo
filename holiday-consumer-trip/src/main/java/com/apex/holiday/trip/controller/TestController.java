package com.apex.holiday.trip.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.apex.holiday.api.TestService;
import com.apex.holiday.dto.HolidayTestDto;

/**
 * @desc: holiday-consumer-trip
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午3:50:01
 * @version: v1.0
 */
@RestController
public class TestController {

    @Autowired
    private TestService holidayTestService;

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 下午3:50:23
     * @return String
     */
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        List<HolidayTestDto> list = holidayTestService.listCity();
        System.out.println(list);
        return "test";
    }

}
