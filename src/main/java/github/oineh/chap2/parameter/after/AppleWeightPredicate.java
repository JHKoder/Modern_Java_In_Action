package github.oineh.chap2.parameter.after;

public class AppleWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple2 apple2) {
        return apple2.getWeight() > 150;
    }
}
