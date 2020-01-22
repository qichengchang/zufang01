package com.sunrui.zufang.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunrui.zufang.bean.User;
import com.sunrui.zufang.dao.UserMapper;
import com.sunrui.zufang.service.UserService;
import com.sunrui.zufang.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Autowired
//    UserDao userDao;
//
//    @Override
//    public ResponseMessage<User> regist(User user) {
//        QueryWrapper<User> wp = new QueryWrapper<User>();
//        wp.eq("userName",user.getUserName());
//        User isuser = userDao.selectOne(wp);
//        if(isuser == null){
//
//        }
//        return null;
//    }
}
