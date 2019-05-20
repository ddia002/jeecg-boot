package org.jeecg.modules.demo.dmm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.dmm.entity.DmmBrand;
import org.jeecg.modules.demo.dmm.service.IDmmBrandService;
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
@RequestMapping("/dmm/dmmBrand")
@Slf4j
public class DmmBrandController {
	@Autowired
	private IDmmBrandService dmmBrandService;
	
	/**
	  * 分页列表查询
	 * @param dmmBrand
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<DmmBrand>> queryPageList(DmmBrand dmmBrand,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DmmBrand>> result = new Result<IPage<DmmBrand>>();
		QueryWrapper<DmmBrand> queryWrapper = new QueryWrapper<DmmBrand>(dmmBrand);
		Page<DmmBrand> page = new Page<DmmBrand>(pageNo,pageSize);
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
		IPage<DmmBrand> pageList = dmmBrandService.page(page, queryWrapper);
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
	 * @param dmmBrand
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DmmBrand> add(@RequestBody DmmBrand dmmBrand) {
		Result<DmmBrand> result = new Result<DmmBrand>();
		try {
			dmmBrandService.save(dmmBrand);
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
	 * @param dmmBrand
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<DmmBrand> eidt(@RequestBody DmmBrand dmmBrand) {
		Result<DmmBrand> result = new Result<DmmBrand>();
		DmmBrand dmmBrandEntity = dmmBrandService.getById(dmmBrand.getBrandId());
		if(dmmBrandEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmBrandService.updateById(dmmBrand);
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
	public Result<DmmBrand> delete(@RequestParam(name="id",required=true) String id) {
		Result<DmmBrand> result = new Result<DmmBrand>();
		DmmBrand dmmBrand = dmmBrandService.getById(id);
		if(dmmBrand==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmBrandService.removeById(id);
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
	public Result<DmmBrand> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DmmBrand> result = new Result<DmmBrand>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dmmBrandService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<DmmBrand> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DmmBrand> result = new Result<DmmBrand>();
		DmmBrand dmmBrand = dmmBrandService.getById(id);
		if(dmmBrand==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dmmBrand);
			result.setSuccess(true);
		}
		return result;
	}

}
