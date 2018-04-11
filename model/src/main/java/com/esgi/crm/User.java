package com.esgi.crm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    public boolean isValid() {
        return isNotBlank(this.firstname)
                && isNotBlank(this.lastname)
                && isAdult();
    }

    protected boolean isAdult() {
        return this.birthdate != null
                && birthdate.plusYears(18).isBefore(now());
    }

}
