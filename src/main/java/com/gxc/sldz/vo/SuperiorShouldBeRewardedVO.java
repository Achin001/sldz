package com.gxc.sldz.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Accessors(chain = true)
public class SuperiorShouldBeRewardedVO {


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


    @ApiModelProperty(value = "关系", example = "")
    @Length(max = 100, message = "关系字长度应小于100")
    @TableField()
    private String relationship;

    @ApiModelProperty(value = "比例", example = "")
    @TableField()
    private double proportion;

    @ApiModelProperty(value = "奖金", example = "")
    @TableField()
    private double bonus;
}


