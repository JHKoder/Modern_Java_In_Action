package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.List;

// 5. 밀라노에 거래자가 있는가?
public class Quiz5 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();
        System.out.println(transactions.stream()
                .anyMatch(tran -> tran.getTrader().getCity().equals("Milan")));
    }
}
