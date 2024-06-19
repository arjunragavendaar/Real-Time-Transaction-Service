package dev.codescreen.pojo;

import dev.codescreen.pojo.DebitorCredit.DebitOrCredit;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class TransactionAmount {
    @NotBlank(message = "Amount is required.")
    String amount;

    @NotBlank(message = "Currency is required.")
    String currency;

    @NotBlank(message = "debitOrCredit is required.")
    DebitOrCredit debitOrCredit;
    
}
