package com.peerless.demo.service;

import com.peerless.demo.dto.TransferRequest;
import com.peerless.demo.model.Transfer;
import com.peerless.demo.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);
    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Transfer scheduleTransfer(TransferRequest request) {
        if (!request.getTransferDate().isAfter(LocalDate.now())) {
            System.out.println(LocalDate.now());
            throw new IllegalArgumentException("Transfer date must be in the future.");
        }

        if (request.getTransferAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }

        System.out.println(LocalDate.now());
        Transfer transfer = new Transfer();
        transfer.setSenderAccountId(request.getSenderAccountId());
        transfer.setRecipientAccountId(request.getRecipientAccountId());
        transfer.setTransferAmount(request.getTransferAmount());
        transfer.setTransferDate(request.getTransferDate());

        return transferRepository.save(transfer);
    }

    public List<Transfer> fetchTransfers(String accountId) {
        return transferRepository.findBySenderAccountIdOrRecipientAccountIdAndCanceledFalse(accountId, accountId);
    }


    public void cancelTransfer(Long transferId) {
        // Find the transfer
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transfer not found with ID: " + transferId));

        // Mark the transfer as canceled
        if (transfer.isCanceled()) {
            logger.warn("Transfer with ID {} is already canceled.", transferId);
            throw new IllegalStateException("Transfer is already canceled.");
        }

        transfer.setCanceled(true);
        transferRepository.save(transfer);

        // Log the action
        logger.info("Transfer with ID {} has been successfully canceled.", transferId);
    }
}