package Controller;

import Model.Global;

public class JailController {
    public enum JailOptions {
        PayBail ("Betal " + Global.JAIL_PRICE),
        RollDice ("Rul terning"),
        OutOfJailCard("Brug Frikort");

        private final String label;

        JailOptions(String s) {
            label = s;
        }

    }
}
