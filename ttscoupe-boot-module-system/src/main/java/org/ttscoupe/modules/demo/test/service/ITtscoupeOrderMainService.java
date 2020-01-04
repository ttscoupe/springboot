package org.ttscoupe.modules.demo.test.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderCustomer;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderMain;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderTicket;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface ITtscoupeOrderMainService extends IService<TtscoupeOrderMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TtscoupeOrderMain ttscoupeOrderMain,List<TtscoupeOrderCustomer> ttscoupeOrderCustomerList,List<TtscoupeOrderTicket> ttscoupeOrderTicketList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TtscoupeOrderMain ttscoupeOrderMain,List<TtscoupeOrderCustomer> ttscoupeOrderCustomerList,List<TtscoupeOrderTicket> ttscoupeOrderTicketList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
}
