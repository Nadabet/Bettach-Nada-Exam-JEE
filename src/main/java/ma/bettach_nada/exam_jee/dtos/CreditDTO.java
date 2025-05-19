package ma.bettach_nada.exam_jee.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditDTO {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String creditType;

    @NotNull
    private LocalDate dateDemande;

    @NotNull
    @Size(max = 50)
    private String statut;

    private LocalDate dateAcceptation;

    @NotNull
    private Double montant;

    @NotNull
    private Integer dureeRemboursement;

    @NotNull
    private Double tauxInteret;

    @Size(max = 255)
    private String motif;

    @Size(max = 50)
    private String typeBien;

    @Size(max = 255)
    private String raisonSociale;

    @NotNull
    private Long client;

}
