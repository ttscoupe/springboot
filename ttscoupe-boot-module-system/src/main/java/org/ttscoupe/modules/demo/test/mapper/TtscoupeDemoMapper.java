package org.ttscoupe.modules.demo.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.ttscoupe.modules.demo.test.entity.TtscoupeDemo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: ttscoupe 测试demo
 * @Author: ttscoupe-boot
 * @Date:  2018-12-29
 * @Version: V1.0
 */
public interface TtscoupeDemoMapper extends BaseMapper<TtscoupeDemo> {

	public List<TtscoupeDemo> getDemoByName(@Param("name") String name);
	
	/**
	 * 查询列表数据 直接传数据权限的sql进行数据过滤
	 * @param page
	 * @param permissionSql
	 * @return
	 */
	public IPage<TtscoupeDemo> queryListWithPermission(Page<TtscoupeDemo> page,@Param("permissionSql")String permissionSql);

}
