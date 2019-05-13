package com.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.model.VO.BeanVO;
import com.model.VO.UserInfoVO;
import com.rabbitMQ.MessageProducer;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Created by lenovo on 2018/4/16.
 * Title HelloContrller
 * Package  com.contrller
 * Description
 *
 * @Version V1.0
 */
public class HelloController implements Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageProducer messageProducer;

   /* @Autowired
    private RedisTemplate redisTemplate;*/
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView();

        String contentPath = request.getContextPath();
        request.getSession().setAttribute("contentPath",contentPath);
        model.addObject("contentPath",contentPath);
        final  UserInfoVO user = new UserInfoVO();
        user.setUsername("xuming");
        user.setUsername("123456");
       // messageProducer.sendMessageToQueue("queueOneKey" ,user);

        model.setViewName("helloWorld");
        return model;
    }
}
