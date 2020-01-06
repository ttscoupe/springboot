package org.ttscoupe.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件获取类
 * Created by wl
 * 2018-10-30 22:00
 * 在yml文件中保存着系统常量参数，通过set,get方法在项目中调用
 */
@Data
@Component
public class SystemProperties {

    @Value(value = "${ttscoupe.path.upload}")
    private String uploadpath;

    @Value(value = "${ttscoupe.path.absload}")
    private String absload;



}
