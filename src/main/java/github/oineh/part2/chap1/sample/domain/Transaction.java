package github.oineh.part2.chap1.sample.domain;

public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + this.trader.toString() + ", "+
            "year: " + this.year+", " +
            "value: " + this.value+"}";
    }

    public boolean isCambridge() {
        return this.trader.getCity().equals("Cambridge");
    }
}
