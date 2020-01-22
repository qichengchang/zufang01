package com.sunrui.zufang.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("house_facilities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facilities {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    @TableField(exist = true, value = "facilities_name")
    @ApiModelProperty(value = "设施名称")
    private String facilitiesName;

    @TableField(exist = true, value = "house_id")
    @ApiModelProperty(value = "房源id")
    private Long houseId;

}
