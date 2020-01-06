package org.ttscoupe.modules.busmodule.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.ttscoupe.common.SystemProperties;
import org.ttscoupe.common.system.util.FileUtils;
import org.ttscoupe.modules.busmodule.entity.BusModule;
import org.ttscoupe.modules.busmodule.mapper.BusModuleMapper;
import org.ttscoupe.modules.busmodule.service.IBusModuleService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 模型表
 * @Author: -boot
 * @Date:   2019-12-29
 * @Version: V1.0
 */
@Service
public class BusModuleServiceImpl extends ServiceImpl<BusModuleMapper, BusModule> implements IBusModuleService {

    @Autowired
    private SystemProperties systemProperties;

    @Override
    public boolean add(BusModule busModule) {
        //原路径
        String inPath = systemProperties.getUploadpath();
        //输出路径
        String outPath = systemProperties.getAbsload();
        StringBuffer objbuf =  new StringBuffer();
        StringBuffer picbuf =  new StringBuffer();
        FileUtils fileUtils = new FileUtils();
        //把文件临时地址转储到正式地址
        if(!StringUtils.isBlank(busModule.getModObj())){
            String[] objs = busModule.getModObj().split(",");
            for (String obj : objs){
                String path = fileUtils.convertFile(obj,inPath,outPath);
                if (!StringUtils.isBlank(path)){
                    if ( objbuf.length() > 0 )
                    {
                        objbuf.append( "," ).append( path );
                    }else{
                        objbuf.append( path );
                    }
                }
            }
        }
        if(!StringUtils.isBlank(busModule.getModPic())){
            String[] pics = busModule.getModPic().split(",");
            for (String pic : pics){
                String picpath = fileUtils.convertFile(pic,inPath,outPath);
                if (!StringUtils.isBlank(picpath)){
                    if ( picbuf.length() > 0 ) {
                        picbuf.append( "," ).append( picpath );
                    }else{
                        picbuf.append( picpath );
                    }
                }
            }
        }
        busModule.setModObj(objbuf.toString());
        busModule.setModPic(picbuf.toString());
        int quest = baseMapper.insert(busModule);
        System.out.println(quest);
        return true;
    }
}
