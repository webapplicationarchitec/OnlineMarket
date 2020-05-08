package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    private String username;

    @Column(nullable = false)
    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    @Email
    private String email;

    private String shippingAddress;

    private String billingAddress;

    private String payment;

    @NotNull
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @NotNull
    @Column(nullable = false)
    private Integer type;
}
