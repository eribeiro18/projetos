package io.github.eribeiro.quarkussocial.domain.repository;

import io.github.eribeiro.quarkussocial.domain.model.Post;
import io.github.eribeiro.quarkussocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {
}
