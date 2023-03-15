package sg.edu.smu.cs301.group3.cardms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Reward;

import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface RewardRepository<T extends Reward, I extends Long> extends JpaRepository<T, I> {

    List<T> findAllByCard(Card card);
    Optional<T> findTopByCardOrderByIdDesc(Card card);

}
