package org.ttscoupe.modules.message.service;

import java.util.List;

import org.ttscoupe.common.system.base.service.TtscoupeService;
import org.ttscoupe.modules.message.entity.SysMessageTemplate;

/**
 * @Description: 消息模板
 * @Author: ttscoupe-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends TtscoupeService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
