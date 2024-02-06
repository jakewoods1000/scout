package com.app.cfd.models.users;

import com.app.cfd.TimeUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Data
public class User {

    private String password;
    private String username;
    private Long version;
    private UUID id;
    private boolean test;

    private Timestamp tokenValidation;

    private Timestamp createdAt;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, boolean test) {
        this.username = username;
        this.password = password;
        this.test = test;
    }

    public User(boolean test) {
        this.test = test;
    }

    static public Long id(Authentication authentication) {
        return Long.parseLong(authentication.getAuthorities().toArray()[0].toString());
    }

    public org.springframework.security.core.userdetails.User securityUser() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(id.toString()));
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(this.getUsername(), this.getPassword(), grantedAuthorities);
    }

    public void markTokenAsValid() {
        TimeUtils time = new TimeUtils();
        setTokenValidation(time.now());
    }

    public boolean validated(){
        return tokenValidation != null;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", version=" + version +
                ", id=" + id +
                ", test=" + test +
                ", tokenValidation=" + tokenValidation +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return test == user.test && Objects.equals(password, user.password) && Objects.equals(username, user.username) && Objects.equals(version, user.version) && Objects.equals(id, user.id) && Objects.equals(tokenValidation, user.tokenValidation) && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username, version, id, test, tokenValidation, createdAt);
    }

}