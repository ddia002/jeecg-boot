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
@TableName("dmm_user_rank")
public class DmmUserRank implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**会员等级表主键*/
	private java.lang.Integer rankId;
	/**会员等级名称*/
	private java.lang.String rankName;
	/**开始积分*/
	private java.lang.Integer startScore;
	/**结束积分*/
	private java.lang.Integer endScore;
	/**折扣*/
	private java.lang.Integer rebate;
	/**用户等级图标*/
	private java.lang.String userrankImg;
	/** 删除标志 -1:删除 1:有效*/
	private java.lang.Integer dataFlag;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
}
