package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account{
    public Admin(String username, @NotEmpty @NotNull String password, @NotEmpty String firstName, @NotEmpty String lastName, AccountStatus accountStatus, @NotEmpty @Email String email) {
        super(username, password, firstName, lastName, accountStatus, email);
    }

//    public Admin(String username, @NotEmpty @NotNull String password, @NotEmpty String firstName, @NotEmpty String lastName, AccountStatus accountStatus, AccountType accountType, @NotEmpty @Email String email) {
//        super(username, password, firstName, lastName, accountStatus, accountType, email);
//    }
}
