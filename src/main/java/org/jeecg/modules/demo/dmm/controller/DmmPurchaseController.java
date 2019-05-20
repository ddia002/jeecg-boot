package org.jeecg.modules.demo.dmm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.dmm.entity.DmmPurchase;
import org.jeecg.modules.demo.dmm.service.IDmmPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Title: Controller
 * @Description: 员工
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@RestController
@RequestMapping("/dmm/dmmPurchase")
@Slf4j
public class DmmPurchaseController {
	@Autowired
	private IDmmPurchaseService dmmPurchaseService;
	
	/**
	  * 分页列表查询
	 * @param dmmPurchase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<DmmPurchase>> queryPageList(DmmPurchase dmmPurchase,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DmmPurchase>> result = new Result<IPage<DmmPurchase>>();
		QueryWrapper<DmmPurchase> queryWrapper = new QueryWrapper<DmmPurchase>(dmmPurchase);
		Page<DmmPurchase> page = new Page<DmmPurchase>(pageNo,pageSize);
		//排序逻辑 处理
		String column = req.getParameter("column");
		String order = req.getParameter("order");
		if(oConvertUtils.isNotEmpty(column) && oConvertUtils.isNotEmpty(order)) {
			if("asc".equals(order)) {
				queryWrapper.orderByAsc(oConvertUtils.camelToUnderline(column));
			}else {
				queryWrapper.orderByDesc(oConvertUtils.camelToUnderline(column));
			}
		}
		IPage<DmmPurchase> pageList = dmmPurchaseService.page(page, queryWrapper);
		//log.debug("查询当前页："+pageList.getCurrent());
		//log.debug("查询当前页数量："+pageList.getSize());
		//log.debug("查询结果数量："+pageList.getRecords().size());
		//log.debug("数据总数："+pageList.getTotal());
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param dmmPurchase
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DmmPurchase> add(@RequestBody DmmPurchase dmmPurchase) {
		Result<DmmPurchase> result = new Result<DmmPurchase>();
		try {
			dmmPurchaseService.save(dmmPurchase);
			result.success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param dmmPurchase
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<DmmPurchase> eidt(@RequestBody DmmPurchase dmmPurchase) {
		Result<DmmPurchase> result = new Result<DmmPurchase>();
		DmmPurchase dmmPurchaseEntity = dmmPurchaseService.getById(dmmPurchase.getPurchaseId());
		if(dmmPurchaseEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmPurchaseService.updateById(dmmPurchase);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<DmmPurchase> delete(@RequestParam(name="id",required=true) String id) {
		Result<DmmPurchase> result = new Result<DmmPurchase>();
		DmmPurchase dmmPurchase = dmmPurchaseService.getById(id);
		if(dmmPurchase==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmPurchaseService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<DmmPurchase> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DmmPurchase> result = new Result<DmmPurchase>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dmmPurchaseService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<DmmPurchase> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DmmPurchase> result = new Result<DmmPurchase>();
		DmmPurchase dmmPurchase = dmmPurchaseService.getById(id);
		if(dmmPurchase==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dmmPurchase);
			result.setSuccess(true);
		}
		return result;
	}

}
