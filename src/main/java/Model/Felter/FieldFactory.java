package Model.Felter;

import Model.Field;

import java.awt.*;

public class FieldFactory {

    public static Field[] makeFields() {

        return new Field[]{
                new StartField("Start", "2M", "Få 2M når du passerer!"),
                new PropertyField("Burgerbaren", "Pris: 1M", "Burgerbaren", 1, Color.gray),
                new PropertyField("Pizzahuset", "Pris: 1M", "Pizzahuset", 1, Color.gray),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Slikbutikken", "Pris: 1M", "Slikbutikken", 1, Color.blue),
                new PropertyField("Iskiosken", "Pris: 1M", "Iskiosken", 1, Color.blue),

                new JailField("Fængsel", "På besøg", "Enten er du endt i fængsel, eller er du på besøg."),

                new PropertyField("Museet", "Pris: 2M", "Museet", 2, Color.pink),
                new PropertyField("Biblioteket", "Pris: 2M", "Biblioteket", 2, Color.pink),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Skaterparken", "Pris: 2M", "Skaterparken", 2, Color.yellow),
                new PropertyField("Swimmingpoolen", "Pris: 2M", "Swimmingpoolen", 2, Color.yellow),

                new FreeParkingField("Fri", "Gratis Parkering", "Hold en fridag"),

                new PropertyField("Spillehallen", "Pris: 3M", "Spillehallen", 3, Color.red),
                new PropertyField("Biograf", "Pris: 3M", "Biograf", 3, Color.red),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Legetøjsbutik", "Pris: 3M", "Legetøjsbutik", 3, Color.orange),
                new PropertyField("Dyrehandel", "Pris: 3M", "Dyrehandel", 3, Color.orange),

                new ToJailField("Fængsel", "Gå til fængsel", "Øv, du skal til fængsel"),

                new PropertyField("Bowlinghallen", "Pris: 4M", "Bowlinghallen", 4, Color.green),
                new PropertyField("Zoo", "Pris: 4M", "Zoo", 4, Color.green),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Vandlandet", "Pris: 5M", "Vandlandet", 5, Color.cyan),
                new PropertyField("Strandpromenaden", "Pris: 5M", "Strandpromenaden", 5, Color.cyan)
        };
    }
}
