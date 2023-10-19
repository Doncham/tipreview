package tipview.toyproject.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Store {
    @Id
    @GeneratedValue
    @Column(name="store_id")
    private Long id;

    private String storeName;

    private String phoneNumber;

    @OneToMany(mappedBy = "store")
    private List<Review> reviewList;
    public Store(){}

    public Store(String storeName, String phoneNumber) {
        this.storeName = storeName;
        this.phoneNumber = phoneNumber;
    }
}
