package com.peerless.demo;

import com.peerless.demo.dto.TransferRequest;
import com.peerless.demo.model.Transfer;
import com.peerless.demo.repository.TransferRepository;
import com.peerless.demo.service.TransferService;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransferServiceTest {
    @Test
    void scheduleTransfer_validRequest_success() {
        TransferRepository repository = mock(TransferRepository.class);
        TransferService service = new TransferService(repository);

        TransferRequest request = new TransferRequest();
        request.setSenderAccountId("123");
        request.setRecipientAccountId("456");
        request.setTransferAmount(BigDecimal.valueOf(1000));
        request.setTransferDate(LocalDate.now().plusDays(1));

        when(repository.save(Mockito.any(Transfer.class))).thenReturn(new Transfer());

        assertDoesNotThrow(() -> service.scheduleTransfer(request));
    }

    @Test
    void cancelTransfer_marksAsCanceled_success() {
        // Mock dependencies
        TransferRepository repository = mock(TransferRepository.class);
        TransferService service = new TransferService(repository);

        // Mock data
        Transfer transfer = new Transfer(1L, "123", "456", BigDecimal.valueOf(1000), LocalDate.now().plusDays(1), false);
        when(repository.findById(1L)).thenReturn(Optional.of(transfer));

        // Call the method
        service.cancelTransfer(1L);

        // Verify the transfer is marked as canceled
        verify(repository, times(1)).save(transfer);
        assert transfer.isCanceled();
    }

    @Test
    void cancelTransfer_alreadyCanceled_throwsException() {
        // Mock dependencies
        TransferRepository repository = mock(TransferRepository.class);
        TransferService service = new TransferService(repository);

        // Mock data
        Transfer transfer = new Transfer(1L, "123", "456", BigDecimal.valueOf(1000), LocalDate.now().plusDays(1), true);
        when(repository.findById(1L)).thenReturn(Optional.of(transfer));

        // Call the method and verify exception
        assertThrows(IllegalStateException.class, () -> service.cancelTransfer(1L));
    }

}

