package org.jeecg.modules.demo.dmm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.dmm.entity.DmmCatsCategory;
import org.jeecg.modules.demo.dmm.service.IDmmCatsCategoryService;
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
@RequestMapping("/dmm/dmmCatsCategory")
@Slf4j
public class DmmCatsCategoryController {
	@Autowired
	private IDmmCatsCategoryService dmmCatsCategoryService;
	
	/**
	  * 分页列表查询
	 * @param dmmCatsCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<DmmCatsCategory>> queryPageList(DmmCatsCategory dmmCatsCategory,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DmmCatsCategory>> result = new Result<IPage<DmmCatsCategory>>();
		QueryWrapper<DmmCatsCategory> queryWrapper = new QueryWrapper<DmmCatsCategory>(dmmCatsCategory);
		Page<DmmCatsCategory> page = new Page<DmmCatsCategory>(pageNo,pageSize);
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
		IPage<DmmCatsCategory> pageList = dmmCatsCategoryService.page(page, queryWrapper);
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
	 * @param dmmCatsCategory
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DmmCatsCategory> add(@RequestBody DmmCatsCategory dmmCatsCategory) {
		Result<DmmCatsCategory> result = new Result<DmmCatsCategory>();
		try {
			dmmCatsCategoryService.save(dmmCatsCategory);
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
	 * @param dmmCatsCategory
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<DmmCatsCategory> eidt(@RequestBody DmmCatsCategory dmmCatsCategory) {
		Result<DmmCatsCategory> result = new Result<DmmCatsCategory>();
		DmmCatsCategory dmmCatsCategoryEntity = dmmCatsCategoryService.getById(dmmCatsCategory.getCategoryId());
		if(dmmCatsCategoryEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmCatsCategoryService.updateById(dmmCatsCategory);
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
	public Result<DmmCatsCategory> delete(@RequestParam(name="id",required=true) String id) {
		Result<DmmCatsCategory> result = new Result<DmmCatsCategory>();
		DmmCatsCategory dmmCatsCategory = dmmCatsCategoryService.getById(id);
		if(dmmCatsCategory==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmCatsCategoryService.removeById(id);
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
	public Result<DmmCatsCategory> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DmmCatsCategory> result = new Result<DmmCatsCategory>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dmmCatsCategoryService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<DmmCatsCategory> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DmmCatsCategory> result = new Result<DmmCatsCategory>();
		DmmCatsCategory dmmCatsCategory = dmmCatsCategoryService.getById(id);
		if(dmmCatsCategory==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dmmCatsCategory);
			result.setSuccess(true);
		}
		return result;
	}

}
