package com.sunrui.zufang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunrui.zufang.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
