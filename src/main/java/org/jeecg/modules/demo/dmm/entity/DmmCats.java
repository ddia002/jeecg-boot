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
@TableName("dmm_cats")
public class DmmCats implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**猫咪主键*/
	private java.lang.Integer catsId;
	/**猫咪编号*/
	private java.lang.String catsCode;
	/**猫咪名称*/
	private java.lang.String catsName;
	/**猫咪公母   0未知  1公  2母*/
	private java.lang.Integer catsSex;
	/**体重(kg)*/
	private java.lang.Double weight;
	/**出生日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date birthday;
	/**猫咪描述(疫苗情况，血统情况以及其他情况描述 )*/
	private java.lang.String description;
	/**猫咪分类id*/
	private java.lang.Integer categoryId;
	/**所属会员id*/
	private java.lang.Integer belongingUserId;
	/**所属供应商id*/
	private java.lang.Integer belongingSupplierId;
	/**删除标识  -1删除   1未删除*/
	private java.lang.Integer dataStatus;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date craeteTime;
}
