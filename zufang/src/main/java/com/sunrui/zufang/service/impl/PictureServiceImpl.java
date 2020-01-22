package com.sunrui.zufang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunrui.zufang.bean.Picture;
import com.sunrui.zufang.bean.User;
import com.sunrui.zufang.dao.PictureMapper;
import com.sunrui.zufang.dao.UserMapper;
import com.sunrui.zufang.service.PictureService;
import com.sunrui.zufang.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {

}
