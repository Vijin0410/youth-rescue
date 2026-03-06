package com.rescue.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 普通用户扩展表（相亲资料）表单信息
 *
 * @author makejava
 * @date 2026-03-05
 */
@Data
@Schema(description = "普通用户扩展表（相亲资料）表单信息")
public class UserProfileForm {

    @Schema(description = "记录ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "性别：0未知 1男 2女")
    private Integer gender;

    @Schema(description = "出生日期")
    private LocalDateTime birthday;

    @Schema(description = "身高(cm)")
    private Integer height;

    @Schema(description = "学历")
    private String education;

    @Schema(description = "职业")
    private String job;

    @Schema(description = "年收入")
    private String annualIncome;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "区县")
    private String district;

    @Schema(description = "微信二维码")
    private String wechatQr;

    @Schema(description = "自我介绍")
    private String selfIntro;

    @Schema(description = "认证状态：0未认证 1已认证")
    private Integer authStatus;

    @Schema(description = "婚姻状况")
    private String marriageStatus;

    @Schema(description = "是否有子女")
    private Boolean hasChildren;

    @Schema(description = "是否想要孩子")
    private Boolean wantChildren;

    @Schema(description = "星座")
    private String zodiac;

    @Schema(description = "兴趣爱好")
    private Object hobby;

    @Schema(description = "创建人ID")
    private Long createBy;

    @Schema(description = "更新人ID")
    private Long updateBy;

}

