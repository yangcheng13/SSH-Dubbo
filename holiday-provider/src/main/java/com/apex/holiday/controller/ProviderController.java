package com.apex.holiday.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.apex.holiday.api.TestService;
import com.apex.holiday.dto.HolidayTestDto;
import com.apex.holiday.dto.ResultVo;
import com.apex.holiday.util.RedisUtil;
import com.google.gson.Gson;

/**
 * @desc: holiday-provider
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午1:48:12
 * @version: v1.0
 */
@Controller
public class ProviderController {

    /**
     * holidayTestService
     */
    @Autowired
    private TestService holidayTestService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 上午9:25:41
     * @param city String
     * @param request HttpServletRequest
     * @return String
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String add(@RequestParam("city") String city,
            @RequestParam("price") String price, HttpServletRequest request,
            HttpServletResponse resp) throws ServletException, IOException {
        if (StringUtils.isBlank(city)) {
            return "error";
        }

        HolidayTestDto vo = new HolidayTestDto();
        vo.setCity(city);
        vo.setPrice(price);
        holidayTestService.add(vo);
        redisUtil.removePattern("*find*");
        resp.sendRedirect("home");
        return null;
    }

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 上午11:55:58
     * @return ModelAndView
     */
    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        List<HolidayTestDto> dtos = holidayTestService.findAll();
        mv.addObject("cityData", dtos);
        return mv;
    }

    /**
     * @author: yangcheng
     * @createTime: 2018年4月8日 下午2:34:07
     * @return ModelAndView
     */
    @RequestMapping(value = "/addCity")
    public ModelAndView addCity() {
        ModelAndView mv = new ModelAndView("add");
        return mv;
    }

    /**
     * @author: yangcheng
     * @createTime: 2018年4月8日 下午2:54:56
     * @return ResultVo<HolidayTestDto>
     */
    @RequestMapping(value = "/editCity", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo editCity(@RequestBody HolidayTestDto vo) {
        holidayTestService.update(vo);
        redisUtil.removePattern("*find*");
        return new ResultVo(true, new Gson().toJson(vo));
    }


    /**
     * @author: yangcheng
     * @createTime: 2018年4月8日 下午2:55:05
     * @return ResultVo<Object>
     */
    @RequestMapping(value = "/delCity", method = RequestMethod.GET)
    public ResultVo delCity(@RequestParam String appId) {
        holidayTestService.delete(appId);
        redisUtil.removePattern("*find*");
        return new ResultVo(true, "success");
    }

}
