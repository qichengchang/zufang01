package com.sunrui.zufang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.sunrui.zufang.bean.Facilities;
import com.sunrui.zufang.bean.HouseInfo;
import com.sunrui.zufang.bean.Picture;
import com.sunrui.zufang.constant.PathConstant;
import com.sunrui.zufang.dao.HouseInfoMapper;
import com.sunrui.zufang.model.AddHouseParm;
import com.sunrui.zufang.model.OrderHouseModel;
import com.sunrui.zufang.model.PageHouseParm;
import com.sunrui.zufang.model.TestParm;
import com.sunrui.zufang.service.HouseInfoService;
import com.sunrui.zufang.service.PictureService;
import com.sunrui.zufang.util.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/house")
@Api(tags = "房源操作")
public class HouseControler {

    @Autowired
    HouseInfoService houseInfoService;
    @Autowired
    PictureService pictureService;
    @Autowired
    HouseInfoMapper houseInfoMapper;


    @PostMapping("/addHouseInfo")
    @ApiOperation("录入房源信息")
    public ResponseMessage<HouseInfo> addHouseInfo(AddHouseParm addHouseParm,@RequestParam List<String> facilities,MultipartFile[] file){
        List<Facilities> facilitiesList = new ArrayList<>();
        if(facilities!=null && facilities.size()>0){
            for (String facilitie : facilities){
                Facilities facilitie1 = new Facilities(null,facilitie,null);
                facilitiesList.add(facilitie1);
            }
        }
        HouseInfo houseInfo1 = houseInfoService.addHouseInfo(new HouseInfo(addHouseParm,facilitiesList),file);
        if(houseInfo1 != null){
            return new ResponseMessage(200,"录入成功",houseInfo1);
        }else{
            return new ResponseMessage(-100,"录入失败",null);
        }
    }
    @PostMapping("/houseInfoIPage")
    @ApiOperation("分页查询房源信息")
    public PageInfo<HouseInfo> houseInfoIPage(@RequestBody PageHouseParm pageHouseParm){
       return houseInfoService.HouseInfoPage(pageHouseParm);
    }

    @GetMapping("/simpleHouseInfo/{id}")
    @ApiOperation("根据房源id查询房源信息")
    public ResponseMessage<HouseInfo> simpleHouseInfo(@PathVariable("id") Long id){
        HouseInfo houseInfo = houseInfoService.simpleHouseInfo(id);
        if(houseInfo == null){
            return new ResponseMessage(-100,"查询失败");
        }
        return new ResponseMessage(200,"查询成功",houseInfo);
    }

    @GetMapping("/rentHouse")
    @ApiOperation("签约租房")
    public String rentHouse(@RequestParam Long userid,@RequestParam Long houseid){
        return houseInfoService.rentHouse(userid,houseid);
    }

    @GetMapping("/orderHouseInfo/{userid}")
    @ApiOperation("查看租房信息")
    public ResponseMessage<OrderHouseModel> orderHouseInfo(@PathVariable("userid") Long userid){
        OrderHouseModel orderHouseModel = houseInfoService.orderHouseInfo(userid);
        if(orderHouseModel == null){
            return new ResponseMessage(-100,"当前用户没有租约信息");
        }
        return new ResponseMessage(200,"查询成功",orderHouseModel);
    }

/*
    @PostMapping("/uploadPhoto")
    @ApiOperation("上传图片")
    public ResponseMessage<List<Picture>> uploadPhoto(HttpServletRequest request){
        if(request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = mrequest.getFiles("file");
            List<Picture> pictureList = new ArrayList<>();
            for(MultipartFile photo:files){
                // 获取文件名
                String photoName  = photo.getOriginalFilename();
                // 拼接绝对路径
                File file = new File( PathConstant.path + photoName );
                file.getParentFile().mkdirs();
                try {
                    photo.transferTo(file);
                    Picture picture = new Picture(null,PathConstant.path + photoName,null);
                    pictureList.add(picture);
                    System.out.println("上传成功:"+ photoName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            pictureService.saveBatch(pictureList);
            System.out.println(pictureList);
            new ResponseMessage(200,"上传成功",pictureList);
        }
        return new ResponseMessage(-100,"上传失败");
    }

 */
}
