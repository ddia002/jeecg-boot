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
@TableName("dmm_goods")
public class DmmGoods implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**商品表主键*/
	private java.lang.Integer goodsId;
	/**商品编号*/
	private java.lang.String goodsCode;
	/**商品货号*/
	private java.lang.String productNo;
	/**商品名称*/
	private java.lang.String goodsName;
	/**商品图片地址*/
	private java.lang.String goodsImg;
	/**市场价格*/
	private java.math.BigDecimal marketPrice;
	/**门店价*/
	private java.math.BigDecimal shopPrice;
	/**预警库存*/
	private java.lang.Integer warnStock;
	/**商品总库存*/
	private java.lang.Integer goodsStock;
	/**单位表id*/
	private java.lang.Integer goodsUnit;
	/**促销信息*/
	private java.lang.Object goodsTips;
	/**是否上架	0:不上架 1:上架*/
	private java.lang.Integer isSale;
	/**是否热销产品	0:否 1:是*/
	private java.lang.Integer isHot;
	/**商品分类ID路径*/
	private java.lang.String goodsCatPath;
	/**最后一级商品分类ID	*/
	private java.lang.Integer goodsCatId;
	/**品牌Id*/
	private java.lang.Integer brandId;
	/**商品描述	*/
	private java.lang.String goodsDesc;
	/**商品状态	-1:违规 0:未审核 1:已审核*/
	private java.lang.Integer goodsStatus;
	/**总销售量*/
	private java.lang.Integer saleNum;
	/**上架时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date saleTime;
	/**删除标志	-1:删除 1:有效*/
	private java.lang.Integer dataStatus;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
}
