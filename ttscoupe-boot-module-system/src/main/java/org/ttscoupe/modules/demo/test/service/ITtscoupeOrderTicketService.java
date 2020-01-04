package org.ttscoupe.modules.demo.test.service;

import java.util.List;

import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderTicket;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单机票
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface ITtscoupeOrderTicketService extends IService<TtscoupeOrderTicket> {
	
	public List<TtscoupeOrderTicket> selectTicketsByMainId(String mainId);
}
