package sg.edu.smu.cs301.group3.cardms.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.helper.DateHelper;
import sg.edu.smu.cs301.group3.cardms.models.*;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.CashbackRewardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.MilesRewardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.PointsRewardRepository;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RewardServiceTest {

    @Mock
    private CardRepository cardRepositoryMock;

    @Mock
    private MilesRewardRepository milesRewardRepository;

    @Mock
    private PointsRewardRepository pointsRewardRepository;

    @Mock
    private CashbackRewardRepository cashbackRewardRepository;

    private RewardService rewardService;

    @BeforeEach
    public void setup() {
        rewardService = new RewardServiceImpl(cardRepositoryMock, milesRewardRepository, pointsRewardRepository, cashbackRewardRepository);

        lenient().when(cardRepositoryMock.findByCardId("card01")).thenReturn(
                Optional.of(new Card().builder()
                        .cardId("card01")
                        .customer(new Customer().builder()
                                .customerId("customer01")
                                .email("kelvin@gmail.com")
                                .cards(new HashSet<Card>()).build())
                        .rewardType(RewardType.miles)
                        .cardType("scis_platinummiles").build()));

        lenient().when(cardRepositoryMock.findByCardId("card02")).thenReturn(
                Optional.of(new Card().builder()
                        .cardId("card02")
                        .customer(new Customer().builder()
                                .customerId("customer01")
                                .email("kelvin@gmail.com")
                                .cards(new HashSet<Card>()).build())
                        .rewardType(RewardType.points)
                        .cardType("scis_shopping").build()));

        lenient().when(cardRepositoryMock.findByCardId("card03")).thenReturn(
                Optional.of(new Card().builder()
                        .cardId("card03")
                        .customer(new Customer().builder()
                                .customerId("customer01")
                                .email("kelvin@gmail.com")
                                .cards(new HashSet<Card>()).build())
                        .rewardType(RewardType.cashback)
                        .cardType("scis_freedom").build()));
    }

    @Test
    public void addEarnedReward_givenMilesEarnedReward_shouldReturnMilesReward() throws ParseException {
        //arrange
        AddRewardDto addRewardDtoStub = new AddRewardDto();

        addRewardDtoStub = addRewardDtoStub.builder()
                .transactionId("trans01")
                .transactionDate(DateHelper.dateFormat().parse("01/03/2023"))
                .cardId("card01")
                .mcc(12345)
                .merchant("Starbuck")
                .currency(Currencies.SGD)
                .amount(10.0)
                .rewardAmount(20.0)
                .rewardBonusAmount(20.0)
                .remarks("TestRemarks").build();

        MilesReward expectedMilesReward = new MilesReward(1L,
                "trans01",  new Card().builder()
                .cardId("card01")
                .customer(new Customer().builder()
                        .customerId("customer01")
                        .email("kelvin@gmail.com")
                        .cards(new HashSet<Card>()).build())
                .rewardType(RewardType.miles)
                .cardType("scis_platinummiles").build(), "Starbuck",
                12345, Currencies.SGD, 10.0,DateHelper.dateFormat().parse("01/03/2023"),
                20.0, 30.0, 50.0, "TestRemarks");

        when(milesRewardRepository.save(any(MilesReward.class))).thenAnswer(invocationOnMock -> {
            MilesReward milesRewardParam = invocationOnMock.getArgument(0);
            milesRewardParam.setId(1L);
            return milesRewardParam;
        });

        //act
        RewardDto result = rewardService.addEarnedReward(addRewardDtoStub);

        //assert
        verify(milesRewardRepository, times(1)).save(expectedMilesReward);
        assertThat(result.getRewardType()).isEqualTo(RewardType.miles);
    }

    @Test
    public void addEarnedReward_givenPointsEarnedReward_shouldProcessAsPointsReward() throws ParseException {
        AddRewardDto addRewardDtoStub = new AddRewardDto();

        addRewardDtoStub = addRewardDtoStub.builder()
                .transactionId("trans01")
                .transactionDate(DateHelper.dateFormat().parse("01/03/2023"))
                .cardId("card02")
                .mcc(12345)
                .merchant("Starbuck")
                .currency(Currencies.SGD)
                .amount(10.0)
                .rewardAmount(20.0)
                .rewardBonusAmount(20.0)
                .remarks("TestRemarks").build();

        PointsReward expectedPointsReward = new PointsReward(1L,
                "trans01",  new Card().builder()
                .cardId("card02")
                .customer(new Customer().builder()
                        .customerId("customer01")
                        .email("kelvin@gmail.com")
                        .cards(new HashSet<Card>()).build())
                .rewardType(RewardType.points)
                .cardType("scis_platinummiles").build(), "Starbuck",
                12345, Currencies.SGD, 10.0,DateHelper.dateFormat().parse("01/03/2023"),
                20.0, 30.0, 50.0, "TestRemarks");

        when(pointsRewardRepository.save(any(PointsReward.class))).thenAnswer(invocationOnMock -> {
            PointsReward pointsRewardParam = invocationOnMock.getArgument(0);
            pointsRewardParam.setId(1L);
            return pointsRewardParam;
        });

        //act
        RewardDto result = rewardService.addEarnedReward(addRewardDtoStub);

        //assert
        verify(pointsRewardRepository, times(1)).save(expectedPointsReward);
        assertThat(result.getRewardType()).isEqualTo(RewardType.points);

    }

    @Test
    public void addEarnedReward_givenCashbackEarnedReward_shouldProcessAsCashbackReward() throws ParseException {
        AddRewardDto addRewardDtoStub = new AddRewardDto();

        addRewardDtoStub = addRewardDtoStub.builder()
                .transactionId("trans01")
                .transactionDate(DateHelper.dateFormat().parse("01/03/2023"))
                .cardId("card03")
                .mcc(12345)
                .merchant("Starbuck")
                .currency(Currencies.SGD)
                .amount(10.0)
                .rewardAmount(20.0)
                .rewardBonusAmount(20.0)
                .remarks("TestRemarks").build();

        CashbackReward expectedCashbackReward = new CashbackReward(1L,
                "trans01",  new Card().builder()
                .cardId("card03")
                .customer(new Customer().builder()
                        .customerId("customer01")
                        .email("kelvin@gmail.com")
                        .cards(new HashSet<Card>()).build())
                .rewardType(RewardType.cashback)
                .cardType("scis_platinummiles").build(), "Starbuck",
                12345, Currencies.SGD, 10.0,DateHelper.dateFormat().parse("01/03/2023"),
                20.0, 30.0, 50.0, "TestRemarks");

        when(cashbackRewardRepository.save(any(CashbackReward.class))).thenAnswer(invocationOnMock -> {
            CashbackReward cashbackRewardParam = invocationOnMock.getArgument(0);
            cashbackRewardParam.setId(1L);
            return cashbackRewardParam;
        });

        //act
        RewardDto result = rewardService.addEarnedReward(addRewardDtoStub);

        //assert
        verify(cashbackRewardRepository, times(1)).save(expectedCashbackReward);
        assertThat(result.getRewardType()).isEqualTo(RewardType.cashback);
    }
}
