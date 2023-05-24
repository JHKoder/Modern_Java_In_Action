package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.List;

//8. 전체 트랜잭션 중 최소값은 얼마인가.
public class Quiz8 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();
        int min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(transactions.get(0).getValue(),Integer::min);
        System.out.println(min);
    }
}
