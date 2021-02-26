package com.springboot.crud_security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
//@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Enumerated(value = EnumType.STRING)
//    @Column(name = "name")
//    private RoleName roleName;
    @Column(name = "name")
//    @JsonValue
    private String roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_roles"
//            , joinColumns = @JoinColumn(name = "role_id")
//            , inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }

    @Override
    public String toString() {
        return getAuthority();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getRoleName() == role.getRoleName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleName());
    }
}
