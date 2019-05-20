package org.jeecg.modules.demo.dmm.service.impl;

import org.jeecg.modules.demo.dmm.entity.DmmUser;
import org.jeecg.modules.demo.dmm.mapper.DmmUserMapper;
import org.jeecg.modules.demo.dmm.service.IDmmUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Service
public class DmmUserServiceImpl extends ServiceImpl<DmmUserMapper, DmmUser> implements IDmmUserService {

}
