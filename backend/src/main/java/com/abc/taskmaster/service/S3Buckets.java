package com.abc.taskmaster.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "aws.s3.buckets")
public class S3Buckets {
    private String attachment;
    private String avatar;

//    public S3Buckets(String attachment, String avatar) {
//        this.attachment = attachment;
//        this.avatar = avatar;
//    }

}
