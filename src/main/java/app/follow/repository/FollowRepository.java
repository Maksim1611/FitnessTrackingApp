package app.follow.repository;

import app.follow.model.Follow;
import app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {

    boolean existsByFollowerAndFollowed(User follower, User following);

    Optional<Follow> findByFollowerAndFollowed(User follower, User following);

    long countByFollower(User follower);

    long countByFollowed(User followed);

    List<Follow> findAllByFollower(User follower);

    List<Follow> findAllByFollowed(User following);

}
