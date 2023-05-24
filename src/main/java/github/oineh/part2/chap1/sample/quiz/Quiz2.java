package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Trader;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.*;

import static java.util.stream.Collectors.toList;

//2. 거래자가 근무하는 모든 도시를 중복없이 나열하시오.
public class Quiz2 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();
        Set<String> traders = new HashSet<>();
        List<Transaction> result = transactions.stream()
                .filter(transaction -> traders.add(transaction.getTrader().getCity()))
                .collect(toList());

        for(Transaction transaction: result){
            System.out.println(transaction.toString());
        }
    }
    // 해설지에는 List<String> cities = stream().map(t -> t.getTrader().getCity()) 지만
    // 근무 도시에서 중복 도시를 없애고 객체는 유지한 상태로 만들어봄
}
