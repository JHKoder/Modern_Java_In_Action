package github.oineh.part1.chap2.parameter.after;

import github.oineh.chap2.parameter.after.Apple2;
import github.oineh.chap2.parameter.after.ApplePredicate;

import static github.oineh.chap2.parameter.after.Color.RED;

public class AppleRedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple2 apple2) {
        return RED.equals(apple2.getColor()) && apple2.getWeight() > 150;
    }
}
