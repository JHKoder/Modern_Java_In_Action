package github.oineh.chap1.stream;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Sample {
    public static void main(String[] args) {
        //고가의 트랜잭션(거래) 만 필터링해 통화로 결과를 그룹화
        //before
        Map<Currency, List<Transaction>> transactionByCurrencies = new HashMap<>();
        List<Transaction> transactions = List.of();
        for(Transaction transaction: transactions){
            if(transaction.getPrice() > 1000){
                Currency currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency =
                        transactionByCurrencies.get(currency);
                if(transactionsForCurrency == null){
                    transactionsForCurrency = new ArrayList<>();
                    transactionByCurrencies.put(currency,transactionsForCurrency);
                }
                transactionsForCurrency.add(transaction);
            }
        }

        //after
        Map<Currency,List<Transaction>> transactionsByCurrencies = transactions.stream()
                        .filter((Transaction t) -> t.getPrice()> 1000)
                        .collect(groupingBy(Transaction::getCurrency));
    }

    private static class Transaction {
        public int getPrice() {
            return 10;
        }

        public Currency getCurrency() {
            return null;
        }
    }
}
