package api.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

/**
 *
 * @author Makhlouf Helali
 */
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "phoneNumber")
})
@Getter
@Setter
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstname;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastname;
    
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 40)
    private String password;

    @NotBlank
    @Size(max = 6)
    private String sex;

    @NotBlank
    @Size(min = 8, max = 8)
    private String phoneNumber;

    private Date birthday;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();

    public Users() {
    }

    public Users(String firstname, String lastname, String username, String password, String sex, String phoneNumber, Date birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    

}
