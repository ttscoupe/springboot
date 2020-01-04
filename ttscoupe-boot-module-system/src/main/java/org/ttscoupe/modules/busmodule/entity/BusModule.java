package org.ttscoupe.modules.busmodule.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.ttscoupe.common.aspect.annotation.Dict;

/**
 * @Description: 模型表
 * @Author: -boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Data
@TableName("bus_module")
public class BusModule implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
	/**模型名称*/
	@Excel(name = "模型名称", width = 15)
	private java.lang.String modName;
	/**模型分类*/
	@Excel(name = "模型分类", width = 15)
	private java.lang.String modType;
	/**预览图*/
	@Excel(name = "预览图", width = 15)
	private java.lang.String modPic;
	/**分享地址*/
	@Excel(name = "分享地址", width = 15)
	private java.lang.String modFxurl;
	/**分享二维码*/
	@Excel(name = "分享二维码", width = 15)
	private java.lang.String modFxscan;
	/**模型*/
	@Excel(name = "模型", width = 15)
	private java.lang.String modObj;
}
