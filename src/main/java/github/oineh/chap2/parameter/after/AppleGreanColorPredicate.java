package github.oineh.chap2.parameter.after;

import static github.oineh.chap2.parameter.after.Color.GREEN;

public class AppleGreanColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple2 apple2) {
        return GREEN.equals(apple2.getColor());
    }
}
