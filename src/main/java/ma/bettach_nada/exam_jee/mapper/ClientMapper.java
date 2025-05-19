package ma.bettach_nada.exam_jee.mapper;
import ma.bettach_nada.exam_jee.Entities.Client;
import ma.bettach_nada.exam_jee.dtos.ClientDTO;

public class ClientMapper {
    public static ClientDTO toDTO(Client client) {
        if (client == null) return null;
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public static Client toEntity(ClientDTO dto) {
        if (dto == null) return null;
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return client;
    }
}
