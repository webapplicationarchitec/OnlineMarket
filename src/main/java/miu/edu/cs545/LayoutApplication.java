package miu.edu.cs545;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.AccountStatus;
import miu.edu.cs545.domain.AccountType;
import miu.edu.cs545.domain.Admin;
import miu.edu.cs545.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LayoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayoutApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AccountRepository accountRepository) {
        return args -> {
//            Account admin = new Admin();
//            admin.setFirstName("Ha");
//            admin.setLastName("Nguyen");
//            admin.setEmail("duongnguyen@miu.edu");
//            admin.setUsername("admin");
//            admin.setAccountStatus(AccountStatus.Approved);
//            admin.setPassword("$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa");
//
//            accountRepository.save(admin);
        };
    }

}