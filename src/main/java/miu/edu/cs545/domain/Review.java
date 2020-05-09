package miu.edu.cs545.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Integer id;
    private Integer productId;
    private Date dateCreate;
    private String comment;
    private ReviewStatus status;

}
