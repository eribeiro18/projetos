package io.github.eribeiro.quarkussocial.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(schema = "public", name = "followers")
@Data
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "follower_id")
    @ManyToOne
    private User follower;
}
