package io.github.eribeiro.quarkussocial.domain.repository;

import io.github.eribeiro.quarkussocial.domain.model.Follower;
import io.github.eribeiro.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    public boolean follows(User follower, User user){
        var params = Parameters.with("follower", follower)
                .and("user", user).map();
        Optional<Follower> result = find(
                "follower = :follower and user = :user", params).firstResultOptional();
        return result.isPresent();
    }

    public List<Follower> findFollowersByUser(Long userId){
        PanacheQuery<Follower> query = find("user.id", userId);
        return query.list();
    }

    public void deleteByFollowerAndUser(Long followerId, Long userId) {
        var params = Parameters.with("followerId", followerId)
                .and("userId", userId).map();
        delete(" id = :followerId and user.id = :userId ", params);
    }
}
