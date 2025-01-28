package com.peerless.demo.repository;

import com.peerless.demo.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findBySenderAccountIdOrRecipientAccountId(String senderAccountId, String recipientAccountId);

    List<Transfer> findBySenderAccountIdOrRecipientAccountIdAndCanceledFalse(
            String senderAccountId, String recipientAccountId);
}