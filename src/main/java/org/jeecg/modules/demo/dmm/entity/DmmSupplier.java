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
@TableName("dmm_supplier")
public class DmmSupplier implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**供应商主键*/
	private java.lang.Integer supplierId;
	/**供应商编号*/
	private java.lang.String supplierCode;
	/**供应商名称*/
	private java.lang.String supplierName;
	/**供应商手机*/
	private java.lang.String supplierPhone;
	/**供应商地址*/
	private java.lang.String address;
	/**供应商状态    0停用  1启用*/
	private java.lang.Integer supplierStatus;
	/**供应商登陆名*/
	private java.lang.String loginName;
	/**供应商登陆密码*/
	private java.lang.String loginPwd;
	/**供应商类型   1.活体 2.食品用品  3服务商*/
	private java.lang.Integer supplierType;
	/**是否合格   0不合格   1合格*/
	private java.lang.Integer qualifiedStatus;
	/**删除标志    -1已删除   1未删除*/
	private java.lang.Integer dateStatus;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date craeteTime;
}
