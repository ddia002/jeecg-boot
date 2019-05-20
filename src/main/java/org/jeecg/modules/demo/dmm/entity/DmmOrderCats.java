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
@TableName("dmm_order_cats")
public class DmmOrderCats implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**订单(活体)主键*/
	private java.lang.Integer orderCatsId;
	/**订单类型 1猫咪*/
	private java.lang.Integer orderType;
	/**订单状态(0:待发货 1:配送中 2:用户确认收货)*/
	private java.lang.Integer orderStatus;
	/**收货方式	0:送货上门 1:自提*/
	private java.lang.Integer deliverType;
	/**客户id*/
	private java.lang.Integer customId;
	/**供应商id*/
	private java.lang.Integer supplierId;
	/**猫咪id*/
	private java.lang.Integer catId;
	/**猫咪金额*/
	private java.math.BigDecimal catsMoney;
	/**运费*/
	private java.math.BigDecimal deliverMoney;
	/**订单总金额  包括运费*/
	private java.math.BigDecimal totalMoney;
	/**实际订单总金额 进行各种折扣之后的金额*/
	private java.math.BigDecimal realTotalMoney;
	/**支付方式  0:货到付款 1:先款后货*/
	private java.lang.Integer payType;
	/**支付来源  1:现金，2：支付宝  3微信*/
	private java.lang.Integer payFrom;
	/**是否支付 0:未支付 1:已支付*/
	private java.lang.Integer isPayFront;
	/**是否支付尾款	0:未支付 1:已支付*/
	private java.lang.Integer isPayAll;
	/**收货人姓名*/
	private java.lang.String userName;
	/**收件人地址*/
	private java.lang.String userAddress;
	/**收件人手机*/
	private java.lang.String userPhone;
	/**所得积分*/
	private java.lang.Integer orderScore;
	/**是否需要发票   1:需要 0:不需要*/
	private java.lang.Integer isInvoice;
	/**发票抬头*/
	private java.lang.String invoiceClient;
	/**订单来源	0:门店 1:微信*/
	private java.lang.Integer orderSrc;
	/**收货时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date receiveTime;
	/**发货时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date deliveryTime;
	/** 订单描述(针对于发猫前的进展信息)*/
	private java.lang.String orderDescription;
	/**订单备注*/
	private java.lang.String orderRemarks;
	/**所属员工id*/
	private java.lang.Integer staffId;
	/**0.未发生退款       1.退款中       2退款完成*/
	private java.lang.Integer refundStatus;
	/**快递公司ID*/
	private java.lang.String expressId;
	/**快递号*/
	private java.lang.String expressNo;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date craeteTime;
}
