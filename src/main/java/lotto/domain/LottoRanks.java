package lotto.domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class LottoRanks {

    public static final int DEFAULT_RATE_OF_RETURN = 0;
    public static final int RATE = 100;

    private final Map<LottoRank, Integer> ranks = new EnumMap<>(LottoRank.class);

    public void add(LottoRank rank) {
        ranks.put(rank, ranks.getOrDefault(rank, 0) + 1);
    }

    public Map<LottoRank, Integer> getRanks() {
        return Map.copyOf(ranks);
    }

    public double calculateTotalReturnRate(Money money) {
        if (money.isEmpty()) {
            return DEFAULT_RATE_OF_RETURN;
        }
        int totalReturn = getTotalReturn(ranks);
        return (double) totalReturn / money.amount() * RATE;
    }

    private int getTotalReturn(Map<LottoRank, Integer> ranks) {
        return ranks.entrySet().stream()
                .mapToInt(this::getReturn)
                .sum();
    }

    private int getReturn(Entry<LottoRank, Integer> rankEntry) {
        return rankEntry.getKey().getRewardAmount() * rankEntry.getValue();
    }

}
