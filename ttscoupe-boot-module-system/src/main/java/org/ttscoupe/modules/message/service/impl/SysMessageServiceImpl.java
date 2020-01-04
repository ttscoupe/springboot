package org.ttscoupe.modules.message.service.impl;

import org.ttscoupe.common.system.base.service.impl.TtscoupeServiceImpl;
import org.ttscoupe.modules.message.entity.SysMessage;
import org.ttscoupe.modules.message.mapper.SysMessageMapper;
import org.ttscoupe.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息
 * @Author: ttscoupe-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageServiceImpl extends TtscoupeServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

}
