package com.controller;

import com.google.gson.Gson;
import com.model.VO.UserInfoVO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lenovo on 2019/5/7.
 * Title loginController
 * Package  com.controller
 * Description
 *
 * @Version V1.0
 */
@Controller
public class loginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public @ResponseBody
    UserInfoVO check(HttpServletRequest request, @RequestBody UserInfoVO user , Model model){
        System.out.println(request.getAttribute("errorMessage"));
        System.out.println(request.getSession().getAttribute("errorMessage"));

        model.addAttribute("errorMessage","超时");
        model.addAttribute("errorCode","errorCode1");
        List<UserInfoVO> users = userService.queryUserByName(user);
        user = null;
        if(null != users && users.size()==1){
            request.getSession().setAttribute("logon","1");
            user = users.get(0);
        }else{

        }
        return user;
    }
}
