package github.oineh.part1.chap2.parameter.after;

import github.oineh.chap2.parameter.after.Apple2;
import github.oineh.chap2.parameter.after.ApplePredicate;

import static github.oineh.chap2.parameter.after.Color.GREEN;

public class AppleGreanColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple2 apple2) {
        return GREEN.equals(apple2.getColor());
    }
}
