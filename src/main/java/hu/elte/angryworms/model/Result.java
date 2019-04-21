package hu.elte.angryworms.model;

import lombok.Data;

@Data
public class Result {
    private final boolean hitOpponent;
    private final boolean shootingHasEnded;

    private Result(final boolean hitOpponent, final boolean shootingHasEnded) {
        this.hitOpponent = hitOpponent;
        this.shootingHasEnded = shootingHasEnded;
    }

    public static Result createWinnerResult() {
        return new Result(true, true);
    }

    public static Result createFaultShootResult() {
        return new Result(false, true);
    }
}
