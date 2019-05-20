package org.jeecg.modules.demo.dmm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.dmm.entity.DmmGoodsCategory;
import org.jeecg.modules.demo.dmm.service.IDmmGoodsCategoryService;
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
@RequestMapping("/dmm/dmmGoodsCategory")
@Slf4j
public class DmmGoodsCategoryController {
	@Autowired
	private IDmmGoodsCategoryService dmmGoodsCategoryService;
	
	/**
	  * 分页列表查询
	 * @param dmmGoodsCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<DmmGoodsCategory>> queryPageList(DmmGoodsCategory dmmGoodsCategory,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DmmGoodsCategory>> result = new Result<IPage<DmmGoodsCategory>>();
		QueryWrapper<DmmGoodsCategory> queryWrapper = new QueryWrapper<DmmGoodsCategory>(dmmGoodsCategory);
		Page<DmmGoodsCategory> page = new Page<DmmGoodsCategory>(pageNo,pageSize);
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
		IPage<DmmGoodsCategory> pageList = dmmGoodsCategoryService.page(page, queryWrapper);
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
	 * @param dmmGoodsCategory
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DmmGoodsCategory> add(@RequestBody DmmGoodsCategory dmmGoodsCategory) {
		Result<DmmGoodsCategory> result = new Result<DmmGoodsCategory>();
		try {
			dmmGoodsCategoryService.save(dmmGoodsCategory);
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
	 * @param dmmGoodsCategory
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<DmmGoodsCategory> eidt(@RequestBody DmmGoodsCategory dmmGoodsCategory) {
		Result<DmmGoodsCategory> result = new Result<DmmGoodsCategory>();
		DmmGoodsCategory dmmGoodsCategoryEntity = dmmGoodsCategoryService.getById(dmmGoodsCategory.getCategoryId());
		if(dmmGoodsCategoryEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmGoodsCategoryService.updateById(dmmGoodsCategory);
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
	public Result<DmmGoodsCategory> delete(@RequestParam(name="id",required=true) String id) {
		Result<DmmGoodsCategory> result = new Result<DmmGoodsCategory>();
		DmmGoodsCategory dmmGoodsCategory = dmmGoodsCategoryService.getById(id);
		if(dmmGoodsCategory==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmGoodsCategoryService.removeById(id);
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
	public Result<DmmGoodsCategory> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DmmGoodsCategory> result = new Result<DmmGoodsCategory>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dmmGoodsCategoryService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<DmmGoodsCategory> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DmmGoodsCategory> result = new Result<DmmGoodsCategory>();
		DmmGoodsCategory dmmGoodsCategory = dmmGoodsCategoryService.getById(id);
		if(dmmGoodsCategory==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dmmGoodsCategory);
			result.setSuccess(true);
		}
		return result;
	}

}
