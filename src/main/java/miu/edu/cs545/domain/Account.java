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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {
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

    @NotNull
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    @Email
    private String email;
}
