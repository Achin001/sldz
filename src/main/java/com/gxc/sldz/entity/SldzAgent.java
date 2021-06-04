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
 * 代理商 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-05-31
 * Copyright © MyCompany
 */
@ApiModel(value = "代理商")
@Getter
@Setter
@Accessors(chain = true)
public class SldzAgent extends BaseCustomEntity {

    private static final long serialVersionUID = -5185016946485966419L;

    /**
     * 等级id
     */
    @ApiModelProperty(value = "等级id", example = "")
    @TableField()
    private Long agentGradeId;

    /**
     * 唯一编号
     */
    @ApiModelProperty(value = "唯一编号", example = "")
    @Length(max = 100, message = "唯一编号长度应小于100")
    @TableField()
    private String agentRandom;

    /**
     * 代理商名字
     */
    @ApiModelProperty(value = "代理商名字", example = "")
    @Length(max = 100, message = "代理商名字长度应小于100")
    @TableField()
    private String agentName;

    /**
     * 代理商手机
     */
    @ApiModelProperty(value = "代理商手机", example = "")
    @Length(max = 100, message = "代理商手机长度应小于100")
    @TableField()
    private String agentPhone;

    /**
     * 代理商密码
     */
    @ApiModelProperty(value = "代理商密码", example = "")
    @Length(max = 100, message = "代理商密码长度应小于100")
    @TableField()
    private String agentPasword;

    /**
     * 性别 1 为男 2为女
     */
    @ApiModelProperty(value = "性别 1 为男 2为女", example = "")
    @Length(max = 100, message = "性别 1 为男 2为女长度应小于100")
    @TableField()
    private String agentGender;

    /**
     * 代理商身份证号码
     */
    @ApiModelProperty(value = "代理商身份证号码", example = "")
    @Length(max = 100, message = "代理商身份证号码长度应小于100")
    @TableField()
    private String agentIdcar;

    /**
     * 省
     */
    @ApiModelProperty(value = "省", example = "")
    @Length(max = 100, message = "省长度应小于100")
    @TableField()
    private String agentSheng;

    /**
     * 市
     */
    @ApiModelProperty(value = "市", example = "")
    @Length(max = 100, message = "市长度应小于100")
    @TableField()
    private String agentShi;

    /**
     * 区/县
     */
    @ApiModelProperty(value = "区/县", example = "")
    @Length(max = 100, message = "区/县长度应小于100")
    @TableField()
    private String agentQu;

    /**
     * 街道
     */
    @ApiModelProperty(value = "街道", example = "")
    @Length(max = 100, message = "街道长度应小于100")
    @TableField()
    private String agentJiedao;

    /**
     * 等级
     */
    @ApiModelProperty(value = "等级", example = "")
    @Length(max = 100, message = "等级长度应小于100")
    @TableField()
    private String agentGrade;

    /**
     * 代理商奖励金
     */
    @ApiModelProperty(value = "代理商奖励金", example = "0.0")
    @TableField()
    private Double agentBonus;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid", example = "")
    @Length(max = 100, message = "微信openid长度应小于100")
    @TableField()
    private String openid;

    /**
     * 微信头像
     */
    @ApiModelProperty(value = "微信头像", example = "")
    @Length(max = 600, message = "微信头像长度应小于600")
    @TableField()
    private String avatarurl;

    /**
     * 代理商积分
     */
    @ApiModelProperty(value = "代理商积分", example = "0.0")
    @TableField()
    private Double agentIntegral;

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
}
