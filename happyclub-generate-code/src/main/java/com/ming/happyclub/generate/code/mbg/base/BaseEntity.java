package com.ming.happyclub.generate.code.mbg.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @description: 数据表基础字段
 * @date 2023/11/21
 * @Replenishment: 创建的数据库表必须包含以下字段,用于生成代码
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除状态：1正常；0删除
     */
    @ApiModelProperty("删除状态：1正常；0删除")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String deleteFlag;
}
