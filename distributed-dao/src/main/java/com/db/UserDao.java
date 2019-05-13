package com.db;

import java.util.List;


import com.annotation.DataSourceChoseAnnotation;
import com.model.VO.BeanVO;
import com.model.VO.UserInfoVO;

/**
 * Created by lenovo on 2018/4/17.
 * Title UserDao
 * Package  com.db
 * Description
 *
 * @Version V1.0
 */
public interface UserDao {
    public List<BeanVO> queryUsers(BeanVO user);

   @DataSourceChoseAnnotation(dataSource = "ONE")
    public int addUser(BeanVO user);

    @DataSourceChoseAnnotation(dataSource = "TWO")
    public List queryAllUsers(BeanVO user);

    @DataSourceChoseAnnotation(dataSource = "TWO")
    public List queryUserByName(BeanVO user);
}
