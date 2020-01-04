package org.ttscoupe.modules.demo.test.service.impl;

import java.util.List;

import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderTicket;
import org.ttscoupe.modules.demo.test.mapper.TtscoupeOrderTicketMapper;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 订单机票
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
@Service
public class TtscoupeOrderTicketServiceImpl extends ServiceImpl<TtscoupeOrderTicketMapper, TtscoupeOrderTicket> implements ITtscoupeOrderTicketService {
	@Autowired
	private TtscoupeOrderTicketMapper ttscoupeOrderTicketMapper;
	
	@Override
	public List<TtscoupeOrderTicket> selectTicketsByMainId(String mainId) {
		return ttscoupeOrderTicketMapper.selectTicketsByMainId(mainId);
	}

}
