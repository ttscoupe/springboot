package org.ttscoupe.modules.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderTicket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单机票
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface TtscoupeOrderTicketMapper extends BaseMapper<TtscoupeOrderTicket> {

	/**
	 *  通过主表外键批量删除客户
	 * @param mainId
	 * @return
	 */
    @Delete("DELETE FROM TTSCOUPE_ORDER_TICKET WHERE ORDER_ID = #{mainId}")
	public boolean deleteTicketsByMainId(String mainId);
    
    
    @Select("SELECT * FROM TTSCOUPE_ORDER_TICKET WHERE ORDER_ID = #{mainId}")
	public List<TtscoupeOrderTicket> selectTicketsByMainId(String mainId);
}
