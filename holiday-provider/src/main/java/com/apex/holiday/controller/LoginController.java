package com.apex.holiday.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.apex.holiday.api.TestService;
import com.apex.holiday.domain.Tuser;

/**
 * @desc: holiday-provider
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午1:48:12
 * @version: v1.0
 */
@Controller
public class LoginController {

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 上午11:55:58
     * @return ModelAndView
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(Tuser user, Model model) {
        ModelAndView v = new ModelAndView("login");
        return v;
    }

    /**
     * @author: yangcheng
     * @createTime: 2018年4月2日 上午11:55:58
     * @return String
     * @throws IOException 
     */
    @RequestMapping(value = "/logon")
    public String logon(Tuser user, Model model,HttpServletResponse resp) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getUserName(), user.getPassword());
        try {
            subject.login(token);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "用户名或密码错误");
            e.printStackTrace();
            return "login";
        }
    }
}
