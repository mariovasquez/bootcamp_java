package com.afp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    private String dni;

    @Column(name = "name", length = 32, unique = false, nullable = false)
    private String name;

    @Column(name = "lastName", length = 32, unique = false, nullable = false)
    private String lastName;
    
    @Column(name = "phone", length = 9, unique = false, nullable = false)
    private String phone;
    
    @Email()
    @Column(name = "email", length = 128, unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name="number_account", unique = false, nullable = false)
    AFP afp;
    
}
