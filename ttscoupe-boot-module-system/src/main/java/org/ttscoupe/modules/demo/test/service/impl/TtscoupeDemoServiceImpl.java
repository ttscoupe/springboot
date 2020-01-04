package org.ttscoupe.modules.demo.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ttscoupe.common.constant.CacheConstant;
import org.ttscoupe.common.system.query.QueryGenerator;
import org.ttscoupe.modules.demo.test.entity.TtscoupeDemo;
import org.ttscoupe.modules.demo.test.mapper.TtscoupeDemoMapper;
import org.ttscoupe.modules.demo.test.service.ITtscoupeDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: ttscoupe 测试demo
 * @Author: ttscoupe-boot
 * @Date:  2018-12-29
 * @Version: V1.0
 */
@Service
public class TtscoupeDemoServiceImpl extends ServiceImpl<TtscoupeDemoMapper, TtscoupeDemo> implements ITtscoupeDemoService {
	@Autowired
	TtscoupeDemoMapper ttscoupeDemoMapper;
	
	/**
	 * 事务控制在service层面
	 * 加上注解：@Transactional，声明的方法就是一个独立的事务（有异常DB操作全部回滚）
	 */
	@Override
	@Transactional
	public void testTran() {
		TtscoupeDemo pp = new TtscoupeDemo();
		pp.setAge(1111);
		pp.setName("测试事务  小白兔 1");
		ttscoupeDemoMapper.insert(pp);
		
		TtscoupeDemo pp2 = new TtscoupeDemo();
		pp2.setAge(2222);
		pp2.setName("测试事务  小白兔 2");
		ttscoupeDemoMapper.insert(pp2);
		
		Integer.parseInt("hello");//自定义异常
		
		TtscoupeDemo pp3 = new TtscoupeDemo();
		pp3.setAge(3333);
		pp3.setName("测试事务  小白兔 3");
		ttscoupeDemoMapper.insert(pp3);
		return ;
	}


	/**
	 * 缓存注解测试： redis
	 */
	@Override
	@Cacheable(cacheNames = CacheConstant.TEST_DEMO_CACHE, key = "#id")
	public TtscoupeDemo getByIdCacheable(String id) {
		TtscoupeDemo t = ttscoupeDemoMapper.selectById(id);
		System.err.println("---未读缓存，读取数据库---");
		System.err.println(t);
		return t;
	}


	@Override
	public IPage<TtscoupeDemo> queryListWithPermission(int pageSize,int pageNo) {
		Page<TtscoupeDemo> page = new Page<>(pageNo, pageSize);
		//编程方式，获取当前请求的数据权限规则SQL片段
		String sql = QueryGenerator.installAuthJdbc(TtscoupeDemo.class);
		return this.baseMapper.queryListWithPermission(page, sql);
	}

}
