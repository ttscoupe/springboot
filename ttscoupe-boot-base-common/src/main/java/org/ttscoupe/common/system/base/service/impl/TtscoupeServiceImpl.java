package org.ttscoupe.common.system.base.service.impl;

import org.ttscoupe.common.system.base.entity.TtscoupeEntity;
import org.ttscoupe.common.system.base.service.TtscoupeService;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: ServiceImpl基类
 * @Author: dangzhenghui@163.com
 * @Date: 2019-4-21 8:13
 * @Version: 1.0
 */
@Slf4j
public class TtscoupeServiceImpl<M extends BaseMapper<T>, T extends TtscoupeEntity> extends ServiceImpl<M, T> implements TtscoupeService<T> {

}
