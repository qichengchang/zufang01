package com.sunrui.zufang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunrui.zufang.bean.HouseInfo;
import com.sunrui.zufang.bean.User;
import com.sunrui.zufang.model.PageHouseParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HouseInfoMapper extends BaseMapper<HouseInfo> {

    public List<HouseInfo> HouseInfoPage(@Param(value = "pageHouseParm") PageHouseParm pageHouseParm);

    public HouseInfo simpleHouseInfo(@Param(value = "houseid") Long id);

}
