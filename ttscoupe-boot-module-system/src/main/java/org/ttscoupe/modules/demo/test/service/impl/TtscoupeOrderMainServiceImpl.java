package org.ttscoupe.modules.demo.test.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderCustomer;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderMain;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderTicket;
import org.ttscoupe.modules.demo.test.mapper.TtscoupeOrderCustomerMapper;
import org.ttscoupe.modules.demo.test.mapper.TtscoupeOrderMainMapper;
import org.ttscoupe.modules.demo.test.mapper.TtscoupeOrderTicketMapper;
import org.ttscoupe.modules.demo.test.service.ITtscoupeOrderMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 订单
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
@Service
public class TtscoupeOrderMainServiceImpl extends ServiceImpl<TtscoupeOrderMainMapper, TtscoupeOrderMain> implements ITtscoupeOrderMainService {

    @Autowired
    private TtscoupeOrderMainMapper ttscoupeOrderMainMapper;
    @Autowired
    private TtscoupeOrderCustomerMapper ttscoupeOrderCustomerMapper;
    @Autowired
    private TtscoupeOrderTicketMapper ttscoupeOrderTicketMapper;

    @Override
    @Transactional
    public void saveMain(TtscoupeOrderMain ttscoupeOrderMain, List<TtscoupeOrderCustomer> ttscoupeOrderCustomerList, List<TtscoupeOrderTicket> ttscoupeOrderTicketList) {
        ttscoupeOrderMainMapper.insert(ttscoupeOrderMain);
        if (ttscoupeOrderCustomerList != null) {
            for (TtscoupeOrderCustomer entity : ttscoupeOrderCustomerList) {
                entity.setOrderId(ttscoupeOrderMain.getId());
                ttscoupeOrderCustomerMapper.insert(entity);
            }
        }
        if (ttscoupeOrderTicketList != null) {
            for (TtscoupeOrderTicket entity : ttscoupeOrderTicketList) {
                entity.setOrderId(ttscoupeOrderMain.getId());
                ttscoupeOrderTicketMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional
    public void updateMain(TtscoupeOrderMain ttscoupeOrderMain, List<TtscoupeOrderCustomer> ttscoupeOrderCustomerList, List<TtscoupeOrderTicket> ttscoupeOrderTicketList) {
        ttscoupeOrderMainMapper.updateById(ttscoupeOrderMain);

        //1.先删除子表数据
        ttscoupeOrderTicketMapper.deleteTicketsByMainId(ttscoupeOrderMain.getId());
        ttscoupeOrderCustomerMapper.deleteCustomersByMainId(ttscoupeOrderMain.getId());

        //2.子表数据重新插入
        if (ttscoupeOrderCustomerList != null) {
            for (TtscoupeOrderCustomer entity : ttscoupeOrderCustomerList) {
                entity.setOrderId(ttscoupeOrderMain.getId());
                ttscoupeOrderCustomerMapper.insert(entity);
            }
        }
        if (ttscoupeOrderTicketList != null) {
            for (TtscoupeOrderTicket entity : ttscoupeOrderTicketList) {
                entity.setOrderId(ttscoupeOrderMain.getId());
                ttscoupeOrderTicketMapper.insert(entity);
            }
        }
    }

	@Override
	@Transactional
	public void delMain(String id) {
		ttscoupeOrderMainMapper.deleteById(id);
		ttscoupeOrderTicketMapper.deleteTicketsByMainId(id);
		ttscoupeOrderCustomerMapper.deleteCustomersByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			ttscoupeOrderMainMapper.deleteById(id);
			ttscoupeOrderTicketMapper.deleteTicketsByMainId(id.toString());
			ttscoupeOrderCustomerMapper.deleteCustomersByMainId(id.toString());
		}
	}

}
