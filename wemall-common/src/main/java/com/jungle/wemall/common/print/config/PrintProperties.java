package com.jungle.wemall.common.print.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description 易联云打印机配置
 * @author jungle
 * @date 2020-03-21 18:32
 */
@Configuration
@ConfigurationProperties(prefix = "wemall.print")
public class PrintProperties {
    private String clientId;
    private String clientSecret;
    private String machineCode;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
}
