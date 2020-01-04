package org.ttscoupe.config.oss;

import org.ttscoupe.common.util.oss.OssBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssBootConfiguration {

    @Value("${ttscoupe.oss.endpoint}")
    private String endpoint;
    @Value("${ttscoupe.oss.accessKey}")
    private String accessKeyId;
    @Value("${ttscoupe.oss.secretKey}")
    private String accessKeySecret;
    @Value("${ttscoupe.oss.bucketName}")
    private String bucketName;
    @Value("${ttscoupe.oss.staticDomain}")
    private String staticDomain;


    @Bean
    public void initOssBootConfiguration() {
        OssBootUtil.setEndPoint(endpoint);
        OssBootUtil.setAccessKeyId(accessKeyId);
        OssBootUtil.setAccessKeySecret(accessKeySecret);
        OssBootUtil.setBucketName(bucketName);
        OssBootUtil.setStaticDomain(staticDomain);
    }
}