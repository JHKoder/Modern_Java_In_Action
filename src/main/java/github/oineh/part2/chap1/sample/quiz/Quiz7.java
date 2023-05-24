package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

//7. 전체 트랜잭션 중 최대값은 얼마인가.
public class Quiz7 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();
        int max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(max);
    }
}
