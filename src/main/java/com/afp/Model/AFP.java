package com.afp.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "afp")
public class AFP {

    @Id
    private String numberAccount;

    @Column(name = "amount", length = 32, unique = false, nullable = false)
    private float amount;

    @Column(name = "withdrawal", length = 32, unique = false, nullable = false)
    private float withdrawal;

    @Column(name = "typeAccount", length = 32, unique = false, nullable = false)
    private String typeAccount;

    @JsonFormat(pattern = "dd::MM::yyyy KK:mm a")
    @Column(name = "withdrawal_date", unique = false, nullable = true)
    private LocalDateTime withdrawalDate;

}
