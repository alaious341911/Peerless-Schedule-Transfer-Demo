package com.peerless.demo.controller;

import com.peerless.demo.dto.TransferRequest;
import com.peerless.demo.model.Transfer;
import com.peerless.demo.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<Transfer> scheduleTransfer(@RequestBody TransferRequest request) {
        Transfer transfer = transferService.scheduleTransfer(request);
        return ResponseEntity.ok(transfer);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transfer>> fetchTransfers(@PathVariable String accountId) {
        List<Transfer> transfers = transferService.fetchTransfers(accountId);
        return ResponseEntity.ok(transfers);
    }

    @DeleteMapping("/cancel/{transferId}")
    public ResponseEntity<Void> cancelTransfer(@PathVariable Long transferId) {
        transferService.cancelTransfer(transferId);
        return ResponseEntity.noContent().build();
    }
}