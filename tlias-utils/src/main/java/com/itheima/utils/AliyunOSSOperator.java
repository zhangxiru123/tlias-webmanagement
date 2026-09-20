package com.itheima.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;


    private OSS ossClient;

    /**
     * 初始化 OSS 客户端（Spring 容器启动时执行）
     */
    @PostConstruct
    public void init() {
        String endpoint =aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region =aliyunOSSProperties.getRegion();
        try {
            ClientBuilderConfiguration config = new ClientBuilderConfiguration();
            config.setSignatureVersion(SignVersion.V4);   // 使用 V4 签名

            CredentialsProvider credentialsProvider =
                    CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

            this.ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(credentialsProvider)
                    .clientConfiguration(config)
                    .region(region)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("OSS 客户端初始化失败", e);
        }
    }

    /**
     * 上传字节数组到 OSS，自动生成按日期分级的路径，并返回可访问的 URL
     * @param content          文件字节内容
     * @param originalFilename 原始文件名（用于提取后缀）
     * @return 文件的访问 URL
     * @throws Exception 上传失败时抛出
     */
    public String upload(byte[] content, String originalFilename) throws Exception {
        String endpoint =aliyunOSSProperties.getEndpoint();
        String bucketName = aliyunOSSProperties.getBucketName();
        String region =aliyunOSSProperties.getRegion();
        // 1. 生成存储路径：yyyy/MM/UUID.后缀
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID() + suffix;
        String objectName = dir + "/" + newFileName;

        // 2. 上传
        PutObjectRequest request = new PutObjectRequest(bucketName, objectName,
                new ByteArrayInputStream(content));
        ossClient.putObject(request);

        // 3. 返回可访问的 URL（外网访问）
        //    格式：https://{bucket}.{endpointHost}/{objectName}
        String host = endpoint.replaceFirst("^https?://", ""); // 去掉协议头
        return "https://" + bucketName + "." + host + "/" + objectName;
    }

    /**
     * 释放资源（Spring 容器销毁时执行）
     */
    @PreDestroy
    public void destroy() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }
}