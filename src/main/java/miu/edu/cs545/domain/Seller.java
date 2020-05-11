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
@DiscriminatorValue("SELLER")
public class Seller extends Account{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private List<Product> listProduct;
}
