package com.jwttest.bean;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure auto-generation for the ID
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    // USER DETAILS METHODS

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the role to a GrantedAuthority
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        // Define your logic; for now, return true (always valid)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Define your logic; for now, return true (always valid)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Define your logic; for now, return true (always valid)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Define your logic; for now, return true (always enabled)
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

