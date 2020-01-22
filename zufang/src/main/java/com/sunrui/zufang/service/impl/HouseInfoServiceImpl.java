package com.sunrui.zufang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunrui.zufang.bean.Facilities;
import com.sunrui.zufang.bean.HouseInfo;
import com.sunrui.zufang.bean.Order;
import com.sunrui.zufang.bean.Picture;
import com.sunrui.zufang.constant.PathConstant;
import com.sunrui.zufang.dao.HouseInfoMapper;
import com.sunrui.zufang.dao.OrderMapper;
import com.sunrui.zufang.model.OrderHouseModel;
import com.sunrui.zufang.model.PageHouseParm;
import com.sunrui.zufang.service.FacilitiesService;
import com.sunrui.zufang.service.HouseInfoService;
import com.sunrui.zufang.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HouseInfoServiceImpl extends ServiceImpl<HouseInfoMapper, HouseInfo> implements HouseInfoService {

    @Autowired
    HouseInfoMapper houseInfoMapper;

    @Autowired
    PictureService pictureService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    FacilitiesService facilitiesService;

    @Override
    @Transactional
    public HouseInfo addHouseInfo(HouseInfo houseInfo,MultipartFile[] file){

        int num = houseInfoMapper.insert(houseInfo);
        if(num>0){
            // 得到房源id
            Long houseId = houseInfo.getId();
            if(houseId != null){
                // 建立设施联系
                if(houseInfo.getFacilities().size() > 0 && houseInfo.getFacilities() != null){
                    for(Facilities facilitie:houseInfo.getFacilities()){
                        facilitie.setHouseId(houseId);
                    }
                    facilitiesService.saveBatch(houseInfo.getFacilities());
                }
                // 上传图片并建立图片联系
                List<Picture> pictureList = new ArrayList<>();
                for (MultipartFile photo : file){
                    String phtotname = photo.getOriginalFilename();
                    File pthotFile = new File(PathConstant.path + phtotname);
                    pthotFile.getParentFile().mkdirs();
                    try {
                        photo.transferTo(pthotFile);
                        pictureList.add(new Picture(null,PathConstant.path + phtotname,houseId));
                        System.out.println("上传成功："+ phtotname);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                pictureService.saveBatch(pictureList);
                houseInfo.setPictures(pictureList);
                return houseInfo;
            }else{
                return null;
            }

        }else{
            return null;
        }
    }

    @Override
    public HouseInfo uploadPhoto(Long houseId,HttpServletRequest request) {
        HouseInfo houseInfo = houseInfoMapper.selectById(houseId);
        List<Picture> pictureList = new ArrayList<>();
        // 如果你现在是MultipartHttpServletRequest的一个对象
        if(request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = mrequest.getFiles("file");
            for(MultipartFile photo:files){
                // 获取文件名
                String photoName  = photo.getOriginalFilename();
                // 拼接绝对路径
                File file = new File(PathConstant.path + photoName );
                file.getParentFile().mkdirs();
                try {
                    photo.transferTo(file);
                    pictureList.add(new Picture(null,PathConstant.path + photoName,houseId));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(houseInfo != null && pictureList != null && pictureList.size() > 0){
                pictureService.saveBatch(pictureList);
                houseInfo.setPictures(pictureList);
                return houseInfo;
            }
        }
        return null;
    }

    @Override
    public PageInfo<HouseInfo> HouseInfoPage(PageHouseParm pageHouseParm) {
        PageHelper.startPage(pageHouseParm.getPageIndex(),pageHouseParm.getPageCount());
        List<HouseInfo> houseInfoIPage = houseInfoMapper.HouseInfoPage(pageHouseParm);
        PageInfo<HouseInfo> page = new PageInfo<HouseInfo>(houseInfoIPage);
        return page;
    }

    @Override
    public HouseInfo simpleHouseInfo(Long id) {
        return houseInfoMapper.simpleHouseInfo(id);
    }

    @Override
    @Transactional
    public String rentHouse(Long userid, Long houseid) {
        // 判断当前用户是否已存在租房信息
        if(orderMapper.selectOne(new QueryWrapper<Order>().eq("user_id",userid).eq("isdaoqi",0)) != null ){
            return "您已有在租合约，无法再次签约。";
        }
        // 将房源状态改为已租
        HouseInfo houseInfo = houseInfoMapper.selectById(houseid);
        houseInfo.setStatus(1);
        int hou = houseInfoMapper.updateById(houseInfo);
        // 生成订单表
        Order order = new Order(null,houseid,userid,new Date(),0);
        int ode = orderMapper.insert(order);
        if(hou>0 && ode>0){
            return "租房成功";
        }else{
            return "租房失败";
        }
    }

    @Override
    public OrderHouseModel orderHouseInfo(Long userid) {
        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("user_id",userid).eq("isdaoqi",0));
        if(order == null){
            return null;
        }
        OrderHouseModel houseModel = new OrderHouseModel(houseInfoMapper.simpleHouseInfo(order.getHouseId()),userid,order.getOrderTime());
        return houseModel;
    }
}
