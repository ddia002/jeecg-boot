package org.jeecg.modules.demo.dmm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.dmm.entity.DmmUser;
import org.jeecg.modules.demo.dmm.service.IDmmUserService;
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
@RequestMapping("/dmm/dmmUser")
@Slf4j
public class DmmUserController {
	@Autowired
	private IDmmUserService dmmUserService;
	
	/**
	  * 分页列表查询
	 * @param dmmUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<DmmUser>> queryPageList(DmmUser dmmUser,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<DmmUser>> result = new Result<IPage<DmmUser>>();
		QueryWrapper<DmmUser> queryWrapper = new QueryWrapper<DmmUser>(dmmUser);
		Page<DmmUser> page = new Page<DmmUser>(pageNo,pageSize);
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
		IPage<DmmUser> pageList = dmmUserService.page(page, queryWrapper);
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
	 * @param dmmUser
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<DmmUser> add(@RequestBody DmmUser dmmUser) {
		Result<DmmUser> result = new Result<DmmUser>();
		try {
			dmmUserService.save(dmmUser);
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
	 * @param dmmUser
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<DmmUser> eidt(@RequestBody DmmUser dmmUser) {
		Result<DmmUser> result = new Result<DmmUser>();
		DmmUser dmmUserEntity = dmmUserService.getById(dmmUser.getUserId());
		if(dmmUserEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmUserService.updateById(dmmUser);
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
	public Result<DmmUser> delete(@RequestParam(name="id",required=true) String id) {
		Result<DmmUser> result = new Result<DmmUser>();
		DmmUser dmmUser = dmmUserService.getById(id);
		if(dmmUser==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = dmmUserService.removeById(id);
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
	public Result<DmmUser> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<DmmUser> result = new Result<DmmUser>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.dmmUserService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<DmmUser> queryById(@RequestParam(name="id",required=true) String id) {
		Result<DmmUser> result = new Result<DmmUser>();
		DmmUser dmmUser = dmmUserService.getById(id);
		if(dmmUser==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(dmmUser);
			result.setSuccess(true);
		}
		return result;
	}

}
