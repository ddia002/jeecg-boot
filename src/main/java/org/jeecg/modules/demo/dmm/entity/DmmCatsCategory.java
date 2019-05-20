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
@TableName("dmm_cats_category")
public class DmmCatsCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**categoryId*/
	private java.lang.Integer categoryId;
	/**父级分类id*/
	private java.lang.Integer parentId;
	/**分类名称*/
	private java.lang.String catName;
	/**分类排序号*/
	private java.lang.Integer catSort;
	/**删除标识   -1删除   1未删除*/
	private java.lang.Integer dataStatus;
	/** 0隐藏     1显示*/
	private java.lang.Integer isShow;
	/**分类描述*/
	private java.lang.String description;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date craeteTime;
}
