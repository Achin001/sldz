package com.gxc.sldz.entity;

import java.util.Date;
import java.lang.Double;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-05-31
 * Copyright © MyCompany
 */
@ApiModel(value = "用户")
@Getter
@Setter
@Accessors(chain = true)
public class SldzUser extends BaseCustomEntity {

    private static final long serialVersionUID = 4247632462024206642L;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid", required = true, example = "微信openid样例内容")
    @NotNull(message = "微信openid不能为空")
    @Length(max = 100, message = "微信openid长度应小于100")
    @TableField()
    private String openid;

    /**
     * 微信头像
     */
    @ApiModelProperty(value = "微信头像", example = "")
    @Length(max = 300, message = "微信头像长度应小于300")
    @TableField()
    private String avatarurl;

    /**
     * 微信城市
     */
    @ApiModelProperty(value = "微信城市", example = "")
    @Length(max = 100, message = "微信城市长度应小于100")
    @TableField()
    private String city;

    /**
     * 微信国家
     */
    @ApiModelProperty(value = "微信国家", example = "")
    @Length(max = 100, message = "微信国家长度应小于100")
    @TableField()
    private String country;

    /**
     * 微信性别
     */
    @ApiModelProperty(value = "微信性别", example = "")
    @Length(max = 100, message = "微信性别长度应小于100")
    @TableField()
    private String gender;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称", example = "")
    @Length(max = 100, message = "微信昵称长度应小于100")
    @TableField()
    private String nickname;

    /**
     * 微信省份
     */
    @ApiModelProperty(value = "微信省份", example = "")
    @Length(max = 100, message = "微信省份长度应小于100")
    @TableField()
    private String province;

    /**
     * 唯一编号
     */
    @ApiModelProperty(value = "唯一编号", example = "")
    @Length(max = 100, message = "唯一编号长度应小于100")
    @TableField()
    private String random;

    @TableField(exist = false)
    private List<String> fileUuidList;
}
