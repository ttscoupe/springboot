package org.ttscoupe.modules.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.ttscoupe.modules.demo.test.entity.TtscoupeOrderCustomer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单客户
 * @Author: ttscoupe-boot
 * @Date:  2019-02-15
 * @Version: V1.0
 */
public interface TtscoupeOrderCustomerMapper extends BaseMapper<TtscoupeOrderCustomer> {
	
	/**
	 *  通过主表外键批量删除客户
	 * @param mainId
	 * @return
	 */
    @Delete("DELETE FROM TTSCOUPE_ORDER_CUSTOMER WHERE ORDER_ID = #{mainId}")
	public boolean deleteCustomersByMainId(String mainId);
    
    @Select("SELECT * FROM TTSCOUPE_ORDER_CUSTOMER WHERE ORDER_ID = #{mainId}")
	public List<TtscoupeOrderCustomer> selectCustomersByMainId(String mainId);
}
