package com.magicformula.process;

import com.magicformula.dao.RankDao;
import com.magicformula.model.Rank;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PopulateRank {

    private RankDao rankDoa;

    public static PopulateRank getInstance() throws SQLException {
        return new PopulateRank();
    }

    private PopulateRank() throws SQLException {
        rankDoa = new RankDao();
    }

    public void populate()  throws SQLException {
        List<Rank> ranks = rankDoa.getAll();

        // Earnings Yield
        Collections.sort(ranks, new Comparator<Rank>() {
            @Override
            public int compare(final Rank object1, final Rank object2) {
                return object2.getEarningsyield().compareTo(object1.getEarningsyield());
            }
        });

        int ranking = 1;
        for (Rank rank : ranks) {
            rank.setEarningsyieldrank(ranking++);
        }

        // Return on Capital
        Collections.sort(ranks, new Comparator<Rank>() {
            @Override
            public int compare(final Rank object1, final Rank object2) {
                return object2.getReturnoncapital().compareTo(object1.getReturnoncapital());
            }
        });

        ranking = 1;
        for (Rank rank : ranks) {
            rank.setReturnoncapitalrank(ranking++);
        }

        // Combined Ranking
        Collections.sort(ranks, new Comparator<Rank>() {
            @Override
            public int compare(final Rank object1, final Rank object2) {
                Integer integer1 = object1.getEarningsyieldrank() + object1.getReturnoncapitalrank();
                Integer integer2 = object2.getEarningsyieldrank() + object2.getReturnoncapitalrank();
                return integer1.compareTo(integer2);
            }
        });

        ranking = 1;
        for (Rank rank : ranks) {
            rank.setCombinedrank(ranking++);
            System.out.println(String.format("%s: %d", rank.getPrimarysymbol(), rank.getCombinedrank()));
            rankDoa.insert(rank);
        }
    }
}
