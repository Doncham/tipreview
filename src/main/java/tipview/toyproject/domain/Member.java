package tipview.toyproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String nickname;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();
    public Member(){}

    public Member(String nickname, String password) {

        this.nickname = nickname;
        this.password = password;
    }
}
