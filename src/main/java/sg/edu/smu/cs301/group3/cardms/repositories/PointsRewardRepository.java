package sg.edu.smu.cs301.group3.cardms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.smu.cs301.group3.cardms.models.PointsReward;

import java.util.Optional;

@Repository
public interface PointsRewardRepository extends JpaRepository<PointsReward, Long> {

    Optional<PointsReward> findTopByOrderByIdDesc();
}
