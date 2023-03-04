package sg.edu.smu.cs301.group3.cardms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Customer;
import sg.edu.smu.cs301.group3.cardms.models.MilesReward;

import java.util.List;
import java.util.Optional;

@Repository
public interface MilesRewardRepository extends JpaRepository<MilesReward, Long> {

    List<MilesReward> findAllByCard(Card card);
    Optional<MilesReward> findTopByOrderByIdDesc();
}
