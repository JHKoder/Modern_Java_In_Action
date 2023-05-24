package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.List;

// 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
public class Quiz4 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();

        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(n1 ,n2) -> n1 + n2);
    }
}
