package ma.bettach_nada.exam_jee.mapper;

import ma.bettach_nada.exam_jee.Entities.Remboursement;
import ma.bettach_nada.exam_jee.dtos.RemboursementDTO;

public class RemboursementMapper {
    public static RemboursementDTO toDTO(Remboursement remboursement) {
        if (remboursement == null) return null;
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        if (remboursement.getCredit() != null) {
            dto.setCredit(remboursement.getCredit().getId());
        }
        return dto;
    }

    public static Remboursement toEntity(RemboursementDTO dto) {
        if (dto == null) return null;
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());
        // Pour le credit, il faut récupérer l'entité Credit par son id dans le service
        return remboursement;
    }
}
