package com.project.wallet.dto;

import com.project.wallet.enums.OperationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {

    @NotNull
    private UUID id;
    @NotNull
    private OperationType operationType;
    @Min(value = 0L)
    private Long amount;

}
