package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name="addressId")
    private Address billingAddress;

    private String payment;

    @NotNull
    @Column(nullable = false)
    private AccountStatus accountStatus;

    //0 - Admin, 1 - Seller, 2 - Buyer
    @NotNull
    @Column(nullable = false)
    private AccountType type;

    @OneToMany
    @JoinColumn(name="accountID")
    private List<Product> listProduct;

    @OneToMany
    @JoinColumn(name="buyerId")
    private List<BonusPoint> listBonusPoints;

    @OneToMany
    @JoinColumn(name="buyerId")
    private List<Order> listOrders;
}
