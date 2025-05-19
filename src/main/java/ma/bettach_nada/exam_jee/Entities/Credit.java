package ma.bettach_nada.exam_jee.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Credits")
@Getter
@Setter
public class Credit {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false, length = 50)
    private String creditType;

    @Column(nullable = false)
    private LocalDate dateDemande;

    @Column(nullable = false, length = 50)
    private String statut;

    @Column
    private LocalDate dateAcceptation;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private Integer dureeRemboursement;

    @Column(nullable = false)
    private Double tauxInteret;

    @Column
    private String motif;

    @Column(length = 50)
    private String typeBien;

    @Column
    private String raisonSociale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "credit")
    private Set<Remboursement> creditRemboursements;

}
