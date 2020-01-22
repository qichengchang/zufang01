package com.sunrui.zufang.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    @TableField(exist=true,value = "username")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @TableField(exist=true,value = "nickname")
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "密码")
    @TableField(exist=true)
    private String password;

    @ApiModelProperty(value = "电话")
    @TableField(exist=true)
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @TableField(exist=true)
    private String mail;

}
