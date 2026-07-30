package app.follow.repository;

import app.follow.model.FollowRequest;
import app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRequestRepository extends JpaRepository<FollowRequest, UUID> {
    boolean existsByRequesterAndTarget(User requester, User target);
    Optional<FollowRequest> findByRequesterAndTarget(User requester, User target);
    List<FollowRequest> findAllByTargetOrderByCreatedAtDesc(User target);
    long countByTarget(User target);
}