package org.ttscoupe.modules.demo.test.service;

import java.util.List;

import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderCustomer;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单客户
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface ITtscoupeOrderCustomerService extends IService<TtscoupeOrderCustomer> {
	
	public List<TtscoupeOrderCustomer> selectCustomersByMainId(String mainId);
}
