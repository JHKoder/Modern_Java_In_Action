package github.oineh.chap2.parameter.after;

import static github.oineh.chap2.parameter.after.Color.RED;

public class AppleRedAndHeavyPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple2 apple2) {
        return RED.equals(apple2.getColor()) && apple2.getWeight() > 150;
    }
}
