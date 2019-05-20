package org.jeecg.modules.demo.dmm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.dmm.entity.DmmCats;
import org.jeecg.modules.demo.dmm.service.IDmmCatsService;
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
@RequestMapping("/dmm/dmmCats")
@Slf4j
public class DmmCatsController {
	@Autowired
	private IDmmCatsService dmmCatsService;
	
	/**
	  * 分页列表查询
	 * @param dmmCats
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<DmmCats>> queryPageList(DmmCats dmmCats,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DmmCats>> result = new Result<IPage<DmmCats>>();
		QueryWrapper<DmmCats> queryWrapper = new QueryWrapper<DmmCats>(dmmCats);
		Page<DmmCats> page = new Page<DmmCats>(pageNo,pageSize);
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
		IPage<DmmCats> pageList = dmmCatsService.page(page, queryWrapper);
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
	 * @param dmmCats
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DmmCats> add(@RequestBody DmmCats dmmCats) {
		Result<DmmCats> result = new Result<DmmCats>();
		try {
			dmmCatsService.save(dmmCats);
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
	 * @param dmmCats
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<DmmCats> eidt(@RequestBody DmmCats dmmCats) {
		Result<DmmCats> result = new Result<DmmCats>();
		DmmCats dmmCatsEntity = dmmCatsService.getById(dmmCats.getCatsId());
		if(dmmCatsEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmCatsService.updateById(dmmCats);
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
	public Result<DmmCats> delete(@RequestParam(name="id",required=true) String id) {
		Result<DmmCats> result = new Result<DmmCats>();
		DmmCats dmmCats = dmmCatsService.getById(id);
		if(dmmCats==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmCatsService.removeById(id);
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
	public Result<DmmCats> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DmmCats> result = new Result<DmmCats>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dmmCatsService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<DmmCats> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DmmCats> result = new Result<DmmCats>();
		DmmCats dmmCats = dmmCatsService.getById(id);
		if(dmmCats==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dmmCats);
			result.setSuccess(true);
		}
		return result;
	}

}
