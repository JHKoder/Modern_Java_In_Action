package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

// 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
public class Quiz1 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();
        List<Transaction> result = transactions.stream()
                .filter( transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        for(Transaction transaction: result){
            System.out.println(transaction.toString());
        }
    }
}
