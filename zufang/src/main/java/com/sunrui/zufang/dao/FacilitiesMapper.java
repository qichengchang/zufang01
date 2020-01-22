package com.sunrui.zufang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunrui.zufang.bean.Facilities;
import com.sunrui.zufang.bean.HouseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FacilitiesMapper extends BaseMapper<Facilities> {

}
