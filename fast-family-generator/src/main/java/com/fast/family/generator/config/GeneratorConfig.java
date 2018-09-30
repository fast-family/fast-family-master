package com.fast.family.generator.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/29-10:20
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneratorConfig {

    private String packageName;

    private String srcBasePath;

    private String dbUser;

    private String dbPassword;

    private String dbName;

    private String dbHost;

    private int port;
}
