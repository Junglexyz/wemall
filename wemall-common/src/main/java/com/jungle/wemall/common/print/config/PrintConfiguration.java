package com.jungle.wemall.common.print.config;

import com.jungle.wemall.common.print.PrintI;
import com.jungle.wemall.common.print.PrintService;
import com.jungle.wemall.common.print.YilianyunPrint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: jungle
 * @date: 2020-03-21 18:36
 */
@Configuration
public class PrintConfiguration {
    private final PrintProperties printProperties;

    public PrintConfiguration(PrintProperties  printProperties){
        this.printProperties = printProperties;
    }

    @Bean
    public PrintService printService(){
        PrintService printService = new PrintService();
        printService.setPrintI(yilianyunPrint());
        return printService;
    }

    @Bean
    public PrintI yilianyunPrint(){
        YilianyunPrint printI = new YilianyunPrint();
        printI.setClientId(printProperties.getClientId());
        printI.setClientSecret(printProperties.getClientSecret());
        printI.setMachineCode(printProperties.getMachineCode());
        return printI;
    }
}
