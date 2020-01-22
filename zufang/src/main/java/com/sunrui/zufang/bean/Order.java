package com.sunrui.zufang.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("house_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(exist = true, value = "house_id")
    @ApiModelProperty("房源id")
    private Long houseId;

    @TableField(exist = true, value = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @TableField(exist = true, value = "order_time")
    @ApiModelProperty("租房时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    @TableField(exist = true, value = "isdaoqi")
    @ApiModelProperty("是否到期  0/未   1/已")
    private Integer isDaoqi;
}
