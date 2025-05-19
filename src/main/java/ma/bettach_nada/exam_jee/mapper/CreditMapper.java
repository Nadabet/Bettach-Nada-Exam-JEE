package ma.bettach_nada.exam_jee.mapper;

import ma.bettach_nada.exam_jee.Entities.Credit;
import ma.bettach_nada.exam_jee.dtos.CreditDTO;

public class CreditMapper {
    public static CreditDTO toDTO(Credit credit) {
        if (credit == null) return null;
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setCreditType(credit.getCreditType());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcceptation(credit.getDateAcceptation());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDureeRemboursement());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setMotif(credit.getMotif());
        dto.setTypeBien(credit.getTypeBien());
        dto.setRaisonSociale(credit.getRaisonSociale());
        if (credit.getClient() != null) {
            dto.setClient(credit.getClient().getId());
        }
        return dto;
    }

    public static Credit toEntity(CreditDTO dto) {
        if (dto == null) return null;
        Credit credit = new Credit();
        credit.setId(dto.getId());
        credit.setCreditType(dto.getCreditType());
        credit.setDateDemande(dto.getDateDemande());
        credit.setStatut(dto.getStatut());
        credit.setDateAcceptation(dto.getDateAcceptation());
        credit.setMontant(dto.getMontant());
        credit.setDureeRemboursement(dto.getDureeRemboursement());
        credit.setTauxInteret(dto.getTauxInteret());
        credit.setMotif(dto.getMotif());
        credit.setTypeBien(dto.getTypeBien());
        credit.setRaisonSociale(dto.getRaisonSociale());
        // Pour le client, il faut récupérer l'entité Client par son id dans le service
        return credit;
    }
}
