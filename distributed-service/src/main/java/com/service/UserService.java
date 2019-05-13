package com.service;

import com.model.VO.Result;
import com.model.VO.UserInfoVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/4/16.
 * Title UserService
 * Package  com.service
 * Description
 *
 * @Version V1.0
 */
public interface UserService{
    public String sayHello(String name);

    public int addUser(UserInfoVO user);

    public int addUsers(List<UserInfoVO> users);

    public Result queryAllUsers(UserInfoVO user ,int pageNum, int pageSize);

    public List<UserInfoVO> queryUserByName(UserInfoVO user);
}
