package org.ttscoupe.modules.demo.test.service;

import org.ttscoupe.common.system.base.service.TtscoupeService;
import org.ttscoupe.modules.demo.test.entity.TtscoupeDemo;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Description: ttscoupe 测试demo
 * @Author: ttscoupe-boot
 * @Date:  2018-12-29
 * @Version: V1.0
 */
public interface ITtscoupeDemoService extends TtscoupeService<TtscoupeDemo> {
	
	public void testTran();
	
	public TtscoupeDemo getByIdCacheable(String id);
	
	/**
	 * 查询列表数据 在service中获取数据权限sql信息
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	IPage<TtscoupeDemo> queryListWithPermission(int pageSize,int pageNo);
}
