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
@TableName("dmm_purchase")
public class DmmPurchase implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**采购表主键*/
	private java.lang.Integer purchaseId;
	/**采购类型(1.进货  2.公司日常用品)*/
	private java.lang.Integer purchaseType;
	/**进货品id*/
	private java.lang.Integer goodsId;
	/**进货品名称*/
	private java.lang.String goodsName;
	/**进货备注*/
	private java.lang.String purchaseMarks;
	/**采购数量*/
	private java.lang.Integer num;
	/**采购单价*/
	private java.math.BigDecimal unitPrice;
	/**采购总价*/
	private java.math.BigDecimal totalPrice;
	/**供货商id*/
	private java.lang.Integer supplierId;
	/**公司经手人id*/
	private java.lang.Integer staffId;
	/**期望到货时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date expectTime;
	/**到货时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date arrivalTime;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date craeteTime;
}
