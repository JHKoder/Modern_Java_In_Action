package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.List;


// 6. 케임브리지에 거주하는 거래자는 모든 트랜잭션값을 출력하시오.
public class Quiz6 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();
        int sum = transactions.stream()
                .filter(Transaction::isCambridge)
                .map(Transaction::getValue)
                .reduce(0,Integer::sum);

        System.out.println(sum);
    }
}
