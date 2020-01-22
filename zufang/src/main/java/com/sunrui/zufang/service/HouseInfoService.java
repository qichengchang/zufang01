package com.sunrui.zufang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sunrui.zufang.bean.HouseInfo;
import com.sunrui.zufang.model.OrderHouseModel;
import com.sunrui.zufang.model.PageHouseParm;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface HouseInfoService extends IService<HouseInfo> {
    public HouseInfo addHouseInfo(HouseInfo houseInfo,MultipartFile[] file);

    public HouseInfo uploadPhoto(Long houseId,HttpServletRequest request);

    public PageInfo<HouseInfo> HouseInfoPage(PageHouseParm pageHouseParm);

    public HouseInfo simpleHouseInfo(Long id);

    public String rentHouse(Long userid, Long houseid);

    public OrderHouseModel orderHouseInfo(Long userid);

}
