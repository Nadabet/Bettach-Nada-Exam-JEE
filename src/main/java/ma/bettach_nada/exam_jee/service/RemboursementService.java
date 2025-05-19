package ma.bettach_nada.exam_jee.service;

import java.util.List;
import ma.bettach_nada.exam_jee.Entities.Credit;
import ma.bettach_nada.exam_jee.Entities.Remboursement;
import ma.bettach_nada.exam_jee.dtos.RemboursementDTO;
import ma.bettach_nada.exam_jee.repos.CreditRepository;
import ma.bettach_nada.exam_jee.repos.RemboursementRepository;
import ma.bettach_nada.exam_jee.Exception.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;

    public RemboursementService(final RemboursementRepository remboursementRepository,
            final CreditRepository creditRepository) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
    }

    public List<RemboursementDTO> findAll() {
        final List<Remboursement> remboursements = remboursementRepository.findAll(Sort.by("id"));
        return remboursements.stream()
                .map(remboursement -> mapToDTO(remboursement, new RemboursementDTO()))
                .toList();
    }

    public RemboursementDTO get(final Long id) {
        return remboursementRepository.findById(id)
                .map(remboursement -> mapToDTO(remboursement, new RemboursementDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final RemboursementDTO remboursementDTO) {
        final Remboursement remboursement = new Remboursement();
        mapToEntity(remboursementDTO, remboursement);
        return remboursementRepository.save(remboursement).getId();
    }

    public void update(final Long id, final RemboursementDTO remboursementDTO) {
        final Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(remboursementDTO, remboursement);
        remboursementRepository.save(remboursement);
    }

    public void delete(final Long id) {
        remboursementRepository.deleteById(id);
    }

    private RemboursementDTO mapToDTO(final Remboursement remboursement,
            final RemboursementDTO remboursementDTO) {
        remboursementDTO.setId(remboursement.getId());
        remboursementDTO.setDate(remboursement.getDate());
        remboursementDTO.setMontant(remboursement.getMontant());
        remboursementDTO.setType(remboursement.getType());
        remboursementDTO.setCredit(remboursement.getCredit() == null ? null : remboursement.getCredit().getId());
        return remboursementDTO;
    }

    private Remboursement mapToEntity(final RemboursementDTO remboursementDTO,
            final Remboursement remboursement) {
        remboursement.setDate(remboursementDTO.getDate());
        remboursement.setMontant(remboursementDTO.getMontant());
        remboursement.setType(remboursementDTO.getType());
        final Credit credit = remboursementDTO.getCredit() == null ? null : creditRepository.findById(remboursementDTO.getCredit())
                .orElseThrow(() -> new NotFoundException("credit not found"));
        remboursement.setCredit(credit);
        return remboursement;
    }

}
