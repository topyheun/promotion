package topy.promotion.modules.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import topy.promotion.modules.common.BaseEntity;
import topy.promotion.modules.promotion.Participation;
import topy.promotion.modules.promotion.Winner;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_username")
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email", unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Participation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Winner> winners = new ArrayList<>();
}
