package com.sunrui.zufang.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class PageHouseParm {

    @ApiModelProperty("起始页")
    private Integer pageIndex;

    @ApiModelProperty("每页条数")
    private Integer pageCount;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("模糊查询（地址）")
    private String keyword;

    @ApiModelProperty("长租/短租")
    private String rentType;

    @ApiModelProperty("最小价格")
    private Integer minPrice;

    @ApiModelProperty("最大价格")
    private Integer maxPrice;

}
