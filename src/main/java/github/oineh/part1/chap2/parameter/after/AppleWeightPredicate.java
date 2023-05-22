package github.oineh.part1.chap2.parameter.after;

import github.oineh.chap2.parameter.after.Apple2;
import github.oineh.chap2.parameter.after.ApplePredicate;

public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple2 apple2) {
        return apple2.getWeight() > 150;
    }
}
