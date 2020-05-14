package miu.edu.cs545.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("BUYER")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Buyer extends Account{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressId")
    @Valid
    private Address billingAddress;

    private String payment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    private List<BonusPoint> bonusPointList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
   // @JoinColumn(name="buyerId")
    private List<OnlineOrder> onlineOrderList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Follower", joinColumns = {@JoinColumn(name="buyerId")}, inverseJoinColumns = {@JoinColumn(name="sellerId")})
    private List<Seller> followerList;

    public Buyer(String username, @NotEmpty @NotNull String password, @NotEmpty String firstName, @NotEmpty String lastName, AccountStatus accountStatus, @NotEmpty @Email String email) {
        super(username, password, firstName, lastName, accountStatus, email);
    }

//    public Buyer(String username, @NotEmpty @NotNull String password, @NotEmpty String firstName, @NotEmpty String lastName, AccountStatus accountStatus, AccountType accountType, @NotEmpty @Email String email) {
//        super(username, password, firstName, lastName, accountStatus, accountType, email);
//    }
}
