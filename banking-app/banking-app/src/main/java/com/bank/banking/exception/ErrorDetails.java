package com.bank.banking.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timstamp,
                           String message,
                           String details,
                           String errorCode) {
}
