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
@TableName("dmm_brand")
public class DmmBrand implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**品牌表主键*/
	private java.lang.Integer brandId;
	/**品牌名称*/
	private java.lang.String brandName;
	/**品牌图标地址*/
	private java.lang.String brandImg;
	/**品牌描述*/
	private java.lang.String brandDesc;
	/**删除标识 -1已删除   1未删除*/
	private java.lang.String dataStatus;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
}
