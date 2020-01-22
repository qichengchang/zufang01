package com.sunrui.zufang.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunrui.zufang.bean.HouseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AddHouseParm {

    @TableField(exist = true, value = "address")
    @ApiModelProperty(value = "地址")
    private String address;

    @TableField(exist = true, value = "area")
    @ApiModelProperty(value = "面积/㎡")
    private Double area;

    @TableField(exist = true, value = "direction")
    @ApiModelProperty(value = "朝向")
    private String direction;

    @TableField(exist = true, value = "house_type")
    @ApiModelProperty(value = "户型")
    private String houseType;

    @TableField(exist = true, value = "floor")
    @ApiModelProperty(value = "楼层")
    private Integer floor;

    @TableField(exist = true, value = "elevator")
    @ApiModelProperty(value = "是否有电梯,字符串 有/无")
    private String elevator;

    @TableField(exist = true, value = "year")
    @ApiModelProperty(value = "房子年代")
    private Integer year;

    @TableField(exist = true, value = "heating ")
    @ApiModelProperty(value = "供暖")
    private String heating;

    @TableField(exist = true, value = "afforestation ")
    @ApiModelProperty(value = "绿化  单位/%")
    private Integer afforestation;

    @TableField(exist = true, value = "price ")
    @ApiModelProperty(value = "价格")
    private Integer price;

    @TableField(exist = true, value = "des")
    @ApiModelProperty(value = "简介描述")
    private String des;

    @TableField(exist = true, value = "rental_time")
    @ApiModelProperty(value = "可入住时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalTime;

    @TableField(exist = true, value = "rent_type")
    @ApiModelProperty(value = "长租短租")
    private String rentType;

    @TableField(exist = true, value = "jingdu")
    @ApiModelProperty(value = "经度")
    private BigDecimal jingdu;

    @TableField(exist = true, value = "weidu")
    @ApiModelProperty(value = "纬度")
    private BigDecimal weidu;

    @TableField(exist = true, value = "city_name")
    @ApiModelProperty(value = "城市名")
    private String cityName;

    @TableField(exist = true, value = "landlord_user_id")
    @ApiModelProperty(value = "房东id")
    private Integer landlordUserId;

    @TableField(exist = true, value = "tenant_user_id")
    @ApiModelProperty(value = "租户id")
    private Integer tenantUserId;

    @TableField(exist = true, value = "status")
    @ApiModelProperty(value = "出租状态")
    private Integer status;

    @TableField(exist = true, value = "contacts_phone")
    @ApiModelProperty(value = "联系人电话")
    private String contactsPhone;

    @TableField(exist = true, value = "contacts_name")
    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;

}
