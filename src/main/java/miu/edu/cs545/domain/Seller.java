package miu.edu.cs545.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Seller extends Account{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    @JsonIgnore
    private List<Product> listProduct;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    @JsonIgnore
    private List<OnlineOrder> onlineOrderList;

    public Seller(String username, @NotEmpty @NotNull String password, @NotEmpty String firstName, @NotEmpty String lastName, AccountStatus accountStatus, @NotEmpty @Email String email) {
        super(username, password, firstName, lastName, accountStatus, email);
    }
//    public Seller(String username, @NotEmpty @NotNull String password, @NotEmpty String firstName, @NotEmpty String lastName, AccountStatus accountStatus, AccountType accountType, @NotEmpty @Email String email) {
//        super(username, password, firstName, lastName, accountStatus, accountType, email);
//    }
}
