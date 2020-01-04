package org.ttscoupe.modules.demo.test.service.impl;

import java.util.List;

import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderCustomer;
import org.ttscoupe.modules.demo.test.mapper.TtscoupeOrderCustomerMapper;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 订单客户
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
@Service
public class TtscoupeOrderCustomerServiceImpl extends ServiceImpl<TtscoupeOrderCustomerMapper, TtscoupeOrderCustomer> implements ITtscoupeOrderCustomerService {

	@Autowired
	private TtscoupeOrderCustomerMapper ttscoupeOrderCustomerMapper;
	
	@Override
	public List<TtscoupeOrderCustomer> selectCustomersByMainId(String mainId) {
		return ttscoupeOrderCustomerMapper.selectCustomersByMainId(mainId);
	}

}
