package sg.edu.smu.cs301.group3.cardms.services;

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
import sg.edu.smu.cs301.group3.cardms.repositories.*;
import sg.edu.smu.cs301.group3.cardms.stubfactories.RewardStubFactory;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Mock
    private CustomerRepository customerRepository;

    private RewardService rewardService;

    @BeforeEach
    public void setup() {
        rewardService = new RewardServiceImpl(cardRepositoryMock, milesRewardRepository, pointsRewardRepository, cashbackRewardRepository, customerRepository);

        Customer customer01 = new Customer().builder()
                .customerId("customer01")
                .email("kelvin@gmail.com")
                .cards(new HashSet<Card>()).build();

        Customer customer02 = new Customer().builder()
                .customerId("customer02")
                .email("Pestal@gmail.com")
                .cards(new HashSet<Card>()).build();

        Card card01 = new Card().builder()
                .cardId("card01")
                .customer(customer01)
                .rewardType(RewardType.miles)
                .cardType("scis_platinummiles").build();

        Card card02 = new Card().builder()
                .cardId("card02")
                .customer(customer01)
                .rewardType(RewardType.points)
                .cardType("scis_shopping").build();

        Card card03 = new Card().builder()
                .cardId("card03")
                .customer(customer01)
                .rewardType(RewardType.cashback)
                .cardType("scis_freedom").build();

        Card card04 = new Card().builder()
                .cardId("card04")
                .customer(customer02)
                .rewardType(RewardType.miles)
                .cardType("scis_platinummiles").build();

        customer01.getCards().addAll(Stream.of(card01, card02, card03).collect(Collectors.toSet()));
        customer02.getCards().addAll(Stream.of(card04).collect(Collectors.toSet()));

        lenient().when(cardRepositoryMock.findByCardId("card01")).thenReturn(
                Optional.of(card01));

        lenient().when(cardRepositoryMock.findByCardId("card02")).thenReturn(
                Optional.of(card02));

        lenient().when(cardRepositoryMock.findByCardId("card03")).thenReturn(
                Optional.of(card03));

        lenient().when(cardRepositoryMock.findByCardId("card04")).thenReturn(
                Optional.of(card04));

        lenient().when(customerRepository.findById("customer01")).thenReturn(
                Optional.of(customer01));

        lenient().when(customerRepository.findById("customer02")).thenReturn(
                Optional.of(customer02));
    }

    @Test
    public void addEarnedReward_givenMilesEarnedReward_shouldReturnMilesReward() throws ParseException {
        //arrange
        AddRewardDto addRewardDtoStub = new AddRewardDto();

        addRewardDtoStub = addRewardDtoStub.builder()
                .transactionId("trans01")
                .transactionDate(new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()))
                .cardId("card01")
                .mcc(12345)
                .merchant("Starbuck")
                .currency(Currencies.SGD)
                .amount(10.0)
                .rewardAmount(20.0)
                .remarks("TestRemarks").build();

        MilesReward expectedMilesReward = new MilesReward("scis",1L,
                "trans01",  new Card().builder()
                .cardId("card01")
                .customer(new Customer().builder()
                        .customerId("customer01")
                        .email("kelvin@gmail.com")
                        .cards(new HashSet<Card>()).build())
                .rewardType(RewardType.miles)
                .cardType("scis_platinummiles").build(), "Starbuck",
                12345, Currencies.SGD, 10.0,new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),
                20.0, 50.0, "TestRemarks", new MilesReward());

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
                .transactionDate(new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()))
                .cardId("card02")
                .mcc(12345)
                .merchant("Starbuck")
                .currency(Currencies.SGD)
                .amount(10.0)
                .rewardAmount(20.0)
                .remarks("TestRemarks").build();

        PointsReward expectedPointsReward = new PointsReward("scis",1L,
                "trans01",  new Card().builder()
                .cardId("card02")
                .customer(new Customer().builder()
                        .customerId("customer01")
                        .email("kelvin@gmail.com")
                        .cards(new HashSet<Card>()).build())
                .rewardType(RewardType.points)
                .cardType("scis_platinummiles").build(), "Starbuck",
                12345, Currencies.SGD, 10.0,new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),
                20.0, 50.0, "TestRemarks", new PointsReward());

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
                .transactionDate(new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()))
                .cardId("card03")
                .mcc(12345)
                .merchant("Starbuck")
                .currency(Currencies.SGD)
                .amount(10.0)
                .rewardAmount(20.0)
                .remarks("TestRemarks").build();

        CashbackReward expectedCashbackReward = new CashbackReward("scis",1L,
                "trans01",  new Card().builder()
                .cardId("card03")
                .customer(new Customer().builder()
                        .customerId("customer01")
                        .email("kelvin@gmail.com")
                        .cards(new HashSet<Card>()).build())
                .rewardType(RewardType.cashback)
                .cardType("scis_platinummiles").build(), "Starbuck",
                12345, Currencies.SGD, 10.0,new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),
                20.0, 50.0, "TestRemarks", new CashbackReward());

        lenient().when(cashbackRewardRepository.save(any(CashbackReward.class))).thenAnswer(invocationOnMock -> {
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

    @Test
    public void getRewards_givenCustomerId_shouldReturnCustomerRewards() throws ParseException {
        //arrange
        List<MilesReward> milesRewardList = Arrays.asList(RewardStubFactory.MILES_REWARD_01_SGD(milesCard01()),
                RewardStubFactory.MILES_REWARD_02_SGD(milesCard01()));

        List<PointsReward> pointsRewardList = Arrays.asList(RewardStubFactory.POINTS_REWARD_01_SGD(pointsCard02()),
                RewardStubFactory.POINTS_REWARD_02_SGD(pointsCard02()));

        List<CashbackReward> cashbackRewardsList = Arrays.asList(RewardStubFactory.CASHBACK_REWARD_01_SGD(cashbackCard03()),
                RewardStubFactory.CASHBACK_REWARD_02_SGD(cashbackCard03()));

        lenient().when(milesRewardRepository.findAllByCard(milesCard01())).thenReturn(milesRewardList);
        lenient().when(pointsRewardRepository.findAllByCard(pointsCard02())).thenReturn(pointsRewardList);
        lenient().when(cashbackRewardRepository.findAllByCard(cashbackCard03())).thenReturn(cashbackRewardsList);

        //act
        List<RewardDto> result = rewardService.getCustomerEarnedRewards("scis", "customer01");


        //assert
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    public void getRewards_giveCardId_shouldReturnRewards() throws ParseException {
        //arrange
        Customer customer01 = new Customer().builder()
                .customerId("customer01")
                .email("kelvin@gmail.com")
                .cards(new HashSet<Card>()).build();

        Customer customer02 = new Customer().builder()
                .customerId("customer02")
                .email("Pestal@gmail.com")
                .cards(new HashSet<Card>()).build();

        Card card01 = new Card().builder()
                .cardId("card01")
                .customer(customer01)
                .rewardType(RewardType.miles)
                .cardType("scis_platinummiles").build();

        Card card04 = new Card().builder()
                .cardId("card04")
                .customer(customer02)
                .rewardType(RewardType.miles)
                .cardType("scis_platinummiles").build();

        customer01.getCards().addAll(Stream.of(card01).collect(Collectors.toSet()));
        customer02.getCards().addAll(Stream.of(card04).collect(Collectors.toSet()));


        lenient().when(milesRewardRepository.findAllByCard(card01)).thenReturn(Arrays.asList(RewardStubFactory.MILES_REWARD_01_SGD(card01)));
        lenient().when(milesRewardRepository.findAllByCard(card04)).thenReturn(Arrays.asList(RewardStubFactory.MILES_REWARD_02_SGD(card04)));

        //act
        List<RewardDto> result = rewardService.getCardEarnedRewards("scis", "customer01", "scis_platinummiles");

        //assert
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }

    private Card milesCard01(){
        return cardRepositoryMock.findByCardId("card01").get();
    }
    private Card pointsCard02() {
        return cardRepositoryMock.findByCardId("card02").get();
    }
    private Card cashbackCard03() {
        return cardRepositoryMock.findByCardId("card03").get();
    }
}
