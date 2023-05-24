package nurdanemin.paymentservice;

import nurdanemin.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.Payment.ServiceBasePackage})
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

}
