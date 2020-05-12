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
@DiscriminatorValue("SELLER")
public class Seller extends Account{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="sellerID")
    private List<Product> listProduct;

    public Seller(String username, @NotEmpty @NotNull String password, @NotEmpty String firstName, @NotEmpty String lastName, AccountStatus accountStatus, @NotEmpty @Email String email) {
        super(username, password, firstName, lastName, accountStatus, email);
    }
}
