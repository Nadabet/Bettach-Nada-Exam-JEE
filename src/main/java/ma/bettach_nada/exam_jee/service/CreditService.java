package ma.bettach_nada.exam_jee.service;

import java.util.List;
import ma.bettach_nada.exam_jee.Entities.Client;
import ma.bettach_nada.exam_jee.Entities.Credit;
import ma.bettach_nada.exam_jee.Entities.Remboursement;
import ma.bettach_nada.exam_jee.dtos.CreditDTO;
import ma.bettach_nada.exam_jee.repos.ClientRepository;
import ma.bettach_nada.exam_jee.repos.CreditRepository;
import ma.bettach_nada.exam_jee.repos.RemboursementRepository;
import ma.bettach_nada.exam_jee.Exception.NotFoundException;
import ma.bettach_nada.exam_jee.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final RemboursementRepository remboursementRepository;

    public CreditService(final CreditRepository creditRepository,
            final ClientRepository clientRepository,
            final RemboursementRepository remboursementRepository) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.remboursementRepository = remboursementRepository;
    }

    public List<CreditDTO> findAll() {
        final List<Credit> credits = creditRepository.findAll(Sort.by("id"));
        return credits.stream()
                .map(credit -> mapToDTO(credit, new CreditDTO()))
                .toList();
    }

    public CreditDTO get(final Long id) {
        return creditRepository.findById(id)
                .map(credit -> mapToDTO(credit, new CreditDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CreditDTO creditDTO) {
        final Credit credit = new Credit();
        mapToEntity(creditDTO, credit);
        return creditRepository.save(credit).getId();
    }

    public void update(final Long id, final CreditDTO creditDTO) {
        final Credit credit = creditRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(creditDTO, credit);
        creditRepository.save(credit);
    }

    public void delete(final Long id) {
        creditRepository.deleteById(id);
    }

    private CreditDTO mapToDTO(final Credit credit, final CreditDTO creditDTO) {
        creditDTO.setId(credit.getId());
        creditDTO.setCreditType(credit.getCreditType());
        creditDTO.setDateDemande(credit.getDateDemande());
        creditDTO.setStatut(credit.getStatut());
        creditDTO.setDateAcceptation(credit.getDateAcceptation());
        creditDTO.setMontant(credit.getMontant());
        creditDTO.setDureeRemboursement(credit.getDureeRemboursement());
        creditDTO.setTauxInteret(credit.getTauxInteret());
        creditDTO.setMotif(credit.getMotif());
        creditDTO.setTypeBien(credit.getTypeBien());
        creditDTO.setRaisonSociale(credit.getRaisonSociale());
        creditDTO.setClient(credit.getClient() == null ? null : credit.getClient().getId());
        return creditDTO;
    }

    private Credit mapToEntity(final CreditDTO creditDTO, final Credit credit) {
        credit.setCreditType(creditDTO.getCreditType());
        credit.setDateDemande(creditDTO.getDateDemande());
        credit.setStatut(creditDTO.getStatut());
        credit.setDateAcceptation(creditDTO.getDateAcceptation());
        credit.setMontant(creditDTO.getMontant());
        credit.setDureeRemboursement(creditDTO.getDureeRemboursement());
        credit.setTauxInteret(creditDTO.getTauxInteret());
        credit.setMotif(creditDTO.getMotif());
        credit.setTypeBien(creditDTO.getTypeBien());
        credit.setRaisonSociale(creditDTO.getRaisonSociale());
        final Client client = creditDTO.getClient() == null ? null : clientRepository.findById(creditDTO.getClient())
                .orElseThrow(() -> new NotFoundException("client not found"));
        credit.setClient(client);
        return credit;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Credit credit = creditRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Remboursement creditRemboursement = remboursementRepository.findFirstByCredit(credit);
        if (creditRemboursement != null) {
            referencedWarning.setKey("credit.remboursement.credit.referenced");
            referencedWarning.addParam(creditRemboursement.getId());
            return referencedWarning;
        }
        return null;
    }

}
