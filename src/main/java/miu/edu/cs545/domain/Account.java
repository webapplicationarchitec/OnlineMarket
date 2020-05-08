package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String shippingAddress;
    private String billingAddress;
    private String payment;
    private Integer type;
}
