package api.model.entities;

import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Makhlouf Helali
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;

    public Roles(ERoles name) {
        this.name = name;
    }
    
    
}
