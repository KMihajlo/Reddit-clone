package com.mkraguje.redditclone.model;

import com.mkraguje.redditclone.model.validator.PasswordsMatch;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@RequiredArgsConstructor
@Getter @Setter
@NoArgsConstructor
@ToString
@PasswordsMatch
public class User implements UserDetails {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    @Size(min = 8, max = 50, message = "Invalid E-mail")
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(length = 100)
    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password should have minimum 8 characters")
    private String password;

    @NonNull
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @NonNull
    @NotEmpty(message = "Please enter a First Name")
    private String firstName;

    @NonNull
    @NotEmpty(message = "Please enter a Last Name")
    private String lastName;

    @Transient
    @Setter(AccessLevel.NONE)
    private String fullName;

    @NonNull
    @NotEmpty(message = "Please enter a Username")
    @Column(nullable = false, unique = true)
    private String alias;

    @Transient
    @NotEmpty(message = "Please enter Password Confirmation")
    private String confirmPassword;

    private String activationCode;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void addRoles(Set<Role> roles){
        roles.forEach(this::addRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
