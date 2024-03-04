package ru.cource.spring.write.model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "username")
    private String userName;
    private String fio;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final List<Login> logins = new ArrayList<>();

    public void addLogin(Login login) {
        this.logins.add(login);
    }
}