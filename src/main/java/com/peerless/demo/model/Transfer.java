package com.peerless.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @Column(nullable = false)
    private String senderAccountId;

    @Column(nullable = false)
    private String recipientAccountId;

    @Column(nullable = false)
    private BigDecimal transferAmount;

    @Column(nullable = false)
    private LocalDate transferDate;

    @Column(nullable = false)
    private boolean canceled = false; // Soft delete flag
}
