package pl.codeconcept.e2d.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_roles",
        joinColumns =@JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id") )
    private Set<Role> role;

    public Users (){
    }

    public Users(String login, String password, Set<Role> role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
