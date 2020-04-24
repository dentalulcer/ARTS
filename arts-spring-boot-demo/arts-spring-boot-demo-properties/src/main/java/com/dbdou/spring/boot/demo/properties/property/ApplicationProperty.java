package com.dbdou.spring.boot.demo.properties.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by dentalulcer
 */
@Data
@Component
public class ApplicationProperty {

    @Value("${application.name}")
    private String name;

    @Value("${application.version}")
    private String version;

}
