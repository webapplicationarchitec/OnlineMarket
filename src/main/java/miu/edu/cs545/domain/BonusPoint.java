package miu.edu.cs545.domain;

import javax.persistence.*;

@Entity
@Table(
        name="BonusPoint",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"seller", "buyer"})
)
public class BonusPoint {
    @Id
    long id;

    @ManyToOne
    @JoinColumn(name="sellerId")
    private Account seller;

    private int points;
}
