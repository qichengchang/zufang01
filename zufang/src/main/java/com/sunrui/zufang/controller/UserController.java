package com.sunrui.zufang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunrui.zufang.bean.User;
import com.sunrui.zufang.dao.UserMapper;
import com.sunrui.zufang.service.UserService;
import com.sunrui.zufang.util.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserService userService;

    @PostMapping("/regist")
    @ApiOperation("用户注册或修改信息（注册不要传id，修改必须传id）")
    public ResponseMessage<User> regist(@RequestBody User user){
        if(user.getId() != null){
            // 修改
            boolean flag = userService.saveOrUpdate(user);
            if (flag){
                return new ResponseMessage<User>(200,"修改成功",user);
            }else{
                return new ResponseMessage<User>(-100,"修改失败",null);
            }
        }else{
            // 注册
            QueryWrapper<User> wp = new QueryWrapper<User>();
            wp.eq("userName",user.getUserName());
            User isuser = userMapper.selectOne(wp);
            if (isuser != null){
                return new ResponseMessage<User>(201,"用户名已存在",null);
            }
            userMapper.insert(user);
            return new ResponseMessage<User>(200,"注册成功",null);
        }
    }

    @GetMapping("/userInfo/{userid}")
    @ApiOperation("通过用户id查询用户详细信息")
    public ResponseMessage<User> userInfo(@PathVariable("userid") Long userId){
        User user = userService.getById(userId);
        if(user!=null){
            return new ResponseMessage<User>(200,"查询成功",user);
        }
        return new ResponseMessage<User>(-100,"查询失败",null);
    }


    @GetMapping("/checkUserName")
    @ApiOperation("判断用户名是否可用")
    public ResponseMessage<String> checkUserName(String userName){
        QueryWrapper<User> wp = new QueryWrapper<User>();
        wp.eq("userName",userName);
        User isuser = userMapper.selectOne(wp);
        if (isuser != null){
            return new ResponseMessage<String>(201,"用户名已存在",null);
        }
        return new ResponseMessage<String>(200,"用户名可用",null);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    //public ResponseMessage<User> login(@RequestBody User user){
    public ResponseMessage<User> login(@RequestBody User user){
        QueryWrapper<User> wp = new QueryWrapper<User>();
        wp.eq("userName",user.getUserName());
        wp.eq("password",user.getPassword());
        User isuser =userMapper.selectOne(wp);
        if(isuser == null){
            return new ResponseMessage<User>(-100,"用户名或密码错误",null);
        }
        request.getSession().setAttribute(isuser.getUserName(),isuser);
        return new ResponseMessage<User>(200,"登录成功",isuser);
    }

    @GetMapping("/allUser")
    @ApiOperation("查看所有用户")
    public ResponseMessage<List<User>> allUser(){
        List<User> users = userMapper.selectList(null);
        return new ResponseMessage<List<User>>(200,"",users);
    }

    @GetMapping("/nowLoginUser")
    @ApiOperation("根据用户名查看用户是否登录")
    public ResponseMessage<User> nowLoginUser(String userName){
        User nowuser = (User) request.getSession().getAttribute(userName);
        if(nowuser == null){
           return new ResponseMessage<User>(200,"该用户当前没有登录");
        }
        return new ResponseMessage<User>(200,"该用户已登录");
    }

    @GetMapping("/loginOut")
    @ApiOperation("退出登录")
    public ResponseMessage<Object> loginOut(String userName){
        User nowuser = (User) request.getSession().getAttribute(userName);
        if(nowuser == null){
            return new ResponseMessage<Object>(-100,"该用户当前未登录，无需退出");
        }
        if(nowuser.getUserName().equals(userName)){
            request.getSession().removeAttribute(userName);
            return new ResponseMessage<Object>(200,"成功退出登录");
        }
        return null;
    }



}
