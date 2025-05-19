package ma.bettach_nada.exam_jee.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RemboursementDTO {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Double montant;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    private Long credit;

}
