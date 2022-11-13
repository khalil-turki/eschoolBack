package edu.esprit.kaddem.dto;

import edu.esprit.kaddem.lib.AbstractDto;
import edu.esprit.kaddem.model.user.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link edu.esprit.kaddem.model.user.Parent} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ParentDto extends AbstractUserDto<Parent> implements Serializable {
    private Integer id;
    private String cin;
    private List<EtudiantDto> etudiants;
}