package com.dbdou.spring.boot.demo.properties.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by dentalulcer
 */
@Data
@Component
@ConfigurationProperties(prefix = "developer")
public class DeveloperProperty {

    private String name;

    private String website;

    private String qq;

    private String phoneNumber;
}
