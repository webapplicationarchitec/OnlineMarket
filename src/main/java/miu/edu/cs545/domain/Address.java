package miu.edu.cs545.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min=5, max = 30, message = "{error.size}")
    private String street;
    @Size(min=2, max = 20, message = "{error.size}")
    private String city;
    @Size(min=2, max = 10, message = "{error.size}")
    private String state;
    @Pattern(regexp = "^\\d{5}$", message = "{error.zipcode}")
    private String zipcode;
}
