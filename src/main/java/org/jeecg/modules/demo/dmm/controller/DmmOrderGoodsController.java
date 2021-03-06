package org.jeecg.modules.demo.dmm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.dmm.entity.DmmOrderGoods;
import org.jeecg.modules.demo.dmm.service.IDmmOrderGoodsService;
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
@RequestMapping("/dmm/dmmOrderGoods")
@Slf4j
public class DmmOrderGoodsController {
	@Autowired
	private IDmmOrderGoodsService dmmOrderGoodsService;
	
	/**
	  * 分页列表查询
	 * @param dmmOrderGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<DmmOrderGoods>> queryPageList(DmmOrderGoods dmmOrderGoods,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DmmOrderGoods>> result = new Result<IPage<DmmOrderGoods>>();
		QueryWrapper<DmmOrderGoods> queryWrapper = new QueryWrapper<DmmOrderGoods>(dmmOrderGoods);
		Page<DmmOrderGoods> page = new Page<DmmOrderGoods>(pageNo,pageSize);
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
		IPage<DmmOrderGoods> pageList = dmmOrderGoodsService.page(page, queryWrapper);
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
	 * @param dmmOrderGoods
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DmmOrderGoods> add(@RequestBody DmmOrderGoods dmmOrderGoods) {
		Result<DmmOrderGoods> result = new Result<DmmOrderGoods>();
		try {
			dmmOrderGoodsService.save(dmmOrderGoods);
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
	 * @param dmmOrderGoods
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<DmmOrderGoods> eidt(@RequestBody DmmOrderGoods dmmOrderGoods) {
		Result<DmmOrderGoods> result = new Result<DmmOrderGoods>();
		DmmOrderGoods dmmOrderGoodsEntity = dmmOrderGoodsService.getById(dmmOrderGoods.getOrderGoodsId());
		if(dmmOrderGoodsEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmOrderGoodsService.updateById(dmmOrderGoods);
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
	public Result<DmmOrderGoods> delete(@RequestParam(name="id",required=true) String id) {
		Result<DmmOrderGoods> result = new Result<DmmOrderGoods>();
		DmmOrderGoods dmmOrderGoods = dmmOrderGoodsService.getById(id);
		if(dmmOrderGoods==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmOrderGoodsService.removeById(id);
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
	public Result<DmmOrderGoods> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DmmOrderGoods> result = new Result<DmmOrderGoods>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dmmOrderGoodsService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<DmmOrderGoods> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DmmOrderGoods> result = new Result<DmmOrderGoods>();
		DmmOrderGoods dmmOrderGoods = dmmOrderGoodsService.getById(id);
		if(dmmOrderGoods==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dmmOrderGoods);
			result.setSuccess(true);
		}
		return result;
	}

}
