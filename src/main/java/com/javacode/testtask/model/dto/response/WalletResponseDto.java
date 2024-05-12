package com.javacode.testtask.model.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record WalletResponseDto(
        BigDecimal balance
) {
}
