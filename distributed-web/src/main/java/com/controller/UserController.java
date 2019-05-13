package com.controller;

import com.alibaba.dubbo.common.json.JSONObject;
import com.base.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.VO.Result;
import com.model.VO.UserInfoVO;
import com.service.UserService;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user" )
public class UserController {
	public static Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value="/list")
	public String list(Model model){
		System.out.println("查询用户信息");
		//Result result = userService.queryAllUsers(null ,Constants,0.PAGE_SIZE);
		//model.addAttribute("users", result);
		return "user_list";
	}

	@RequestMapping(value="/add")
	public String add(HttpServletRequest request, String username, String password, Model model){
		String contentPath = request.getContextPath();
		request.getSession().setAttribute("logon", "1");
		model.addAttribute("contentPath", contentPath);
		return "user_add";
	}

	@RequestMapping(value="/addsubmit")
	public String add2(UserInfoVO userInfo){

		System.out.println("编号："+userInfo.getNumber());
		System.out.println("姓名："+userInfo.getUsername());
		System.out.println("密码："+userInfo.getPassword());
		userService.addUser(userInfo);
		return "redirect:/user/list";
	}

	@RequestMapping(value="/ajaxCheck",method = RequestMethod.POST)
	public @ResponseBody UserInfoVO check(HttpServletRequest request,@RequestBody UserInfoVO user , Model model){
		System.out.println(request.getAttribute("errorMessage"));
		System.out.println(request.getSession().getAttribute("errorMessage"));

		model.addAttribute("errorMessage","超时");
		model.addAttribute("errorCode","errorCode1");
		List<UserInfoVO> users = userService.queryUserByName(user);

		if(null != users && users.size()==1){
			user = users.get(0);
			model.addAttribute("userInfo", user);
			Gson gson = new Gson();
			String json = gson.toJson(users.get(0));
			System.out.println(json);
			user = gson.fromJson(json ,user.getClass());
			System.out.println(user);
		}else{

		}
		user.setErrorMessage(request.getAttribute("errorMessage")==null?null:(String)request.getAttribute("errorMessage"));
		return user;
	}
	@RequestMapping(value="/ajaxQuery",method = RequestMethod.POST)
	public @ResponseBody
	Result ajaxQuer(@RequestBody HashMap formMap){
		UserInfoVO user =null;
		Gson gson =new Gson();
		user = gson.fromJson(gson.toJson(formMap.get("user")),UserInfoVO.class);
		Result result = userService.queryAllUsers(user,
				Integer.parseInt((String)formMap.get("pageNum")),Integer.parseInt((String)formMap.get("pageSize")));
		return result;
	}
}
