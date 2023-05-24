package github.oineh.part2.chap1.sample.quiz;

import github.oineh.part2.chap1.sample.domain.Data;
import github.oineh.part2.chap1.sample.domain.Transaction;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

//3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
public class Quiz3 {
    public static void main(String[] args) {
        List<Transaction> transactions  = Data.get();
        List<Transaction> result = transactions.stream()
                .filter(tran -> tran.getTrader().getCity().equals("Cambridge"))
                .sorted(comparing(transaction -> transaction.getTrader().getName()))
                .collect(toList());

        for(Transaction transaction: result){
            System.out.println(transaction.toString());
        }
    }
}
