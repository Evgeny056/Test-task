package com.javacode.testtask.model.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import static com.javacode.testtask.util.ErrorMessageResponse.FIELD_NULL_ERROR;
import static com.javacode.testtask.util.ErrorMessageResponse.INCORRECT_VALUE_AMOUNT;
import static com.javacode.testtask.util.ErrorMessageResponse.OPERATION_NOT_SUPPORTED;

@Data
public class RequestChangeWalletDto {

    @NotNull(message = FIELD_NULL_ERROR)
    private UUID walletId;

    @NotNull(message = FIELD_NULL_ERROR)
    @Pattern(regexp = "DEPOSIT|WITHDRAW", message = OPERATION_NOT_SUPPORTED)
    private String operationType;

    @NotNull(message = FIELD_NULL_ERROR)
    @DecimalMin(value = "0.01", message = INCORRECT_VALUE_AMOUNT)
    private BigDecimal amount;

}
