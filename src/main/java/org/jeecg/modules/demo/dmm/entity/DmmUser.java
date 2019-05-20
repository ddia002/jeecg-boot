package org.jeecg.modules.demo.dmm.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 员工
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Data
@TableName("dmm_user")
public class DmmUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**会员表主键*/
	private java.lang.Integer userId;
	/**登陆名*/
	private java.lang.String loginName;
	/**密码*/
	private java.lang.String loginPwd;
	/**0保密  1男 2女*/
	private java.lang.Integer userSex;
	/**昵称*/
	private java.lang.String nickName;
	/**真实姓名*/
	private java.lang.String trueName;
	/**出生日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date brithday;
	/**用户头像地址*/
	private java.lang.String userPhoto;
	/**用户手机*/
	private java.lang.String userPhone;
	/**用户积分*/
	private java.lang.Integer userScore;
	/**用户剩余积分*/
	private java.lang.Integer userSurplusScore;
	/**用户编号*/
	private java.lang.String userCode;
	/**收入水平(1:0-2000    2:2000-5000   3:5000-1w   4:1w+)*/
	private java.lang.Integer income;
	/**职业 1.学生 2白领 3个体*/
	private java.lang.Integer profession;
	/**推荐人id*/
	private java.lang.Integer refereeUser;
	/**会员等级id*/
	private java.lang.Integer rankId;
	/**用户账号状态 0:停用 1:启用*/
	private java.lang.Integer userStatus;
	/**	-1:删除 1:有效*/
	private java.lang.Integer dataFlag;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
}
