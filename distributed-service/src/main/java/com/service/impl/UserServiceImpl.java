package com.service.impl;

import com.base.Constants;
import com.db.UserDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.model.VO.BeanVO;
import com.model.VO.Result;
import com.model.VO.UserInfoVO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/4/16.
 * Title UserServiceImpl
 * Package  com.service.impl
 * Description
 *
 * @Version V1.0
 */
@Service
public class UserServiceImpl implements UserService , Serializable {

    @Autowired
    private UserDao userdao;

    public String sayHello(String name) {
        UserInfoVO user = new UserInfoVO();
        user.setUsername(name);
        user.setPassword(name);

       // userdao.addUser(user);

        return "hello "+ name;
    }

    @Override

    public int addUser(UserInfoVO user) {
        return userdao.addUser(user);
    }
    @Override
    public int addUsers(List<UserInfoVO> users) {
        int i=0;
        for (UserInfoVO user:users){
            this.addUser(user);
            i++;
        }
        return i;
    }
    @Override
    public Result queryAllUsers(UserInfoVO user ,int pageNum, int pageSize) {
        Page<?> page = PageHelper.startPage(pageNum,pageSize);
        List<BeanVO> users = userdao.queryAllUsers(user);
        Result result = new Result();
        result.setTotalCount(page.getTotal());
        result.setTotalPage(page.getTotal() % Constants.PAGE_SIZE==0 ? page.getTotal() %Constants.PAGE_SIZE
                :page.getTotal() / Constants.PAGE_SIZE +1);
        result.setContent(users);
        return result;
    }

    @Override
    public List<UserInfoVO> queryUserByName(UserInfoVO user) {
        List<UserInfoVO> users = null;
        try {
            users = userdao.queryUserByName(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
