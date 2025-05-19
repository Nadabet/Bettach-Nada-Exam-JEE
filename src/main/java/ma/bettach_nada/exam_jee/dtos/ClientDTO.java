package ma.bettach_nada.exam_jee.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nom;

    @NotNull
    @Size(max = 255)
    private String email;

}
