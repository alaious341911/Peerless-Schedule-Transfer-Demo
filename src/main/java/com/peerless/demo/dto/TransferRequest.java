package com.peerless.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferRequest {
    private String senderAccountId;
    private String recipientAccountId;
    private BigDecimal transferAmount;
    private LocalDate transferDate;
}