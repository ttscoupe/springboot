package org.ttscoupe.modules.message.service.impl;

import org.ttscoupe.common.system.base.service.impl.TtscoupeServiceImpl;
import org.ttscoupe.modules.message.entity.SysMessageTemplate;
import org.ttscoupe.modules.message.mapper.SysMessageTemplateMapper;
import org.ttscoupe.modules.message.service.ISysMessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 消息模板
 * @Author: ttscoupe-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageTemplateServiceImpl extends TtscoupeServiceImpl<SysMessageTemplateMapper, SysMessageTemplate> implements ISysMessageTemplateService {

    @Autowired
    private SysMessageTemplateMapper sysMessageTemplateMapper;


    @Override
    public List<SysMessageTemplate> selectByCode(String code) {
        return sysMessageTemplateMapper.selectByCode(code);
    }
}
