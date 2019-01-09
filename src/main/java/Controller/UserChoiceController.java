package Controller;

import Model.Global;

public class UserChoiceController {
    public enum UserChoiceActions {
        PayPercentage ("Betal 10%"),
        PayConstant ("Betal " + Global.FIELD_5);

        private final String label;

        UserChoiceActions(String s) {
            label = s;
        }

    }
}
