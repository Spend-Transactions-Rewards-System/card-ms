package sg.edu.smu.cs301.group3.cardms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.CashbackReward;

import java.util.List;
import java.util.Optional;

@Repository
public interface CashbackRewardRepository extends JpaRepository<CashbackReward, Long> {

    Optional<CashbackReward> findTopByOrderByIdDesc();

    List<CashbackReward> findAllByCard(Card card);
    Optional<CashbackReward> findTopByCardOrderByIdDesc(Card card);
}
