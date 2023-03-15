package sg.edu.smu.cs301.group3.cardms.stubfactories;

import sg.edu.smu.cs301.group3.cardms.helper.DateHelper;
import sg.edu.smu.cs301.group3.cardms.models.*;

import java.sql.Date;
import java.text.ParseException;

public class RewardStubFactory {

    public static MilesReward MILES_REWARD_01_SGD(Card card) throws ParseException {
        return new MilesReward("scis",1L,"trans01", card, "merchant01", 11111, Currencies.SGD, 10.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 14.0,  "Base 1.4 Miles/SGD", null);
    }

    public static MilesReward MILES_REWARD_02_SGD(Card card) throws ParseException {
        return new MilesReward("scis",2L,"trans02", card, "merchant02", 22222, Currencies.SGD, 10.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 28.0,  "Base 1.4 Miles/SGD", 1L);
    }

    public static PointsReward POINTS_REWARD_01_SGD(Card card) throws ParseException {
        return new PointsReward("scis",1L,"trans03", card, "merchant03", 11111, Currencies.SGD, 10.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),10.0, 10.0,  "Base 1 point/SGD", null);
    }

    public static PointsReward POINTS_REWARD_02_SGD(Card card) throws ParseException {
        return new PointsReward("scis",2L,"trans04", card, "merchant04", 22222, Currencies.SGD, 10.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),10.0, 20.0,  "Base 1 point/SGD", 1L);
    }

    public static CashbackReward CASHBACK_REWARD_01_SGD(Card card) throws ParseException {
        return new CashbackReward("scis",1L,"trans05", card, "merchant03", 11111, Currencies.SGD, 100.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),0.5, 0.5,  "0.5% on all spend", null);
    }

    public static CashbackReward CASHBACK_REWARD_02_SGD(Card card) throws ParseException {
        return new CashbackReward("scis",2L,"trans06", card, "merchant04", 22222, Currencies.SGD, 100.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),0.5, 1.0,  "0.5% on all spend", 1L);
    }


}
