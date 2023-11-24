package kz.iitu.javaLabs.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "roles")
@Entity
public class Role extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> userList;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
