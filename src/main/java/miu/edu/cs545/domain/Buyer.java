package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("BUYER")
public class Buyer extends Account{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressId")
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
}
