package miu.edu.cs545.domain;

import javax.persistence.*;

@Entity
@Table(
        name="BonusPoint",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"sellerId", "buyerId"})
)
public class BonusPoint {
    @Id
    long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sellerId")
    private Seller seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="buyerId")
    private Buyer buyer;

    private int points;
}
