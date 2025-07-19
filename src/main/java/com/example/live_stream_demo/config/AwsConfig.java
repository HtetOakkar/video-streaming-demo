package com.example.live_stream_demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ivs.IvsClient;
import software.amazon.awssdk.services.ivschat.IvschatAsyncClient;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class AwsConfig {
    @Value("${aws.access_key}")
    private String accessKey;

    @Value("${aws.secret_key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    private AwsBasicCredentials awsBasicCredentials() {
        return  AwsBasicCredentials.create(accessKey, secretKey);
    }

    @Bean
    IvsClient ivsClient() {
        AwsBasicCredentials awsCreds = awsBasicCredentials();
        return IvsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.of(region))
                .build();
    }


    @Bean
    IvschatAsyncClient ivschatAsyncClient() {
        AwsBasicCredentials awsCreds = awsBasicCredentials();
        return IvschatAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}
