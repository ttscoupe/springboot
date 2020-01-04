package org.ttscoupe;

import java.util.List;

import javax.annotation.Resource;

import org.ttscoupe.modules.demo.mock.MockController;
import org.ttscoupe.modules.demo.test.entity.TtscoupeDemo;
import org.ttscoupe.modules.demo.test.mapper.TtscoupeDemoMapper;
import org.ttscoupe.modules.demo.test.service.ITtscoupeDemoService;
import org.ttscoupe.modules.system.service.ISysDataLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleTest {

	@Resource
	private TtscoupeDemoMapper ttscoupeDemoMapper;
	@Resource
	private ITtscoupeDemoService ttscoupeDemoService;
	@Resource
	private ISysDataLogService sysDataLogService;
	@Resource
	private MockController mock;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<TtscoupeDemo> userList = ttscoupeDemoMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}

	@Test
	public void testXmlSql() {
		System.out.println(("----- selectAll method test ------"));
		List<TtscoupeDemo> userList = ttscoupeDemoMapper.getDemoByName("Sandy12");
		userList.forEach(System.out::println);
	}

	/**
	 * 测试事务
	 */
	@Test
	public void testTran() {
		ttscoupeDemoService.testTran();
	}
	
	//author:lvdandan-----date：20190315---for:添加数据日志测试----
	/**
	 * 测试数据日志添加
	 */
	@Test
	public void testDataLogSave() {
		System.out.println(("----- datalog test ------"));
		String tableName = "ttscoupe_demo";
		String dataId = "4028ef81550c1a7901550c1cd6e70001";
		String dataContent = mock.sysDataLogJson();
		sysDataLogService.addDataLog(tableName, dataId, dataContent);
	}
	//author:lvdandan-----date：20190315---for:添加数据日志测试----
}
