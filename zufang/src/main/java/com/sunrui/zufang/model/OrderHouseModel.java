package com.sunrui.zufang.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunrui.zufang.bean.HouseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHouseModel {

    private HouseInfo houseInfo;

    private Long userId;

    @ApiModelProperty("签约时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
}
