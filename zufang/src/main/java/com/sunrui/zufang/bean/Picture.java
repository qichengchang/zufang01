package com.sunrui.zufang.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("house_picture")
@Api(tags = "图片")
public class Picture {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    @TableField(exist = true, value = "path")
    @ApiModelProperty(value = "图片路径")
    private String path;

    @TableField(exist = true, value = "house_id")
    @ApiModelProperty(value = "房源id")
    private Long houseId;

}
