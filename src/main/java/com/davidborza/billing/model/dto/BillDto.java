package com.davidborza.billing.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Data
public class BillDto {

    private Long id;

    @NotBlank
    private String buyersName;

    @NotEmpty
    private LocalDateTime createdDate;

    @NotBlank
    private LocalDateTime dueDate;

    @NotBlank
    private String itemName;

    @NotBlank
    private String comment;

    @NotBlank
    private BigDecimal price;
}
