package Model.Fields;

import java.awt.*;

public class FieldFactory {

    public static Field[] makeFields() {

        return new Field[]{
                new StartField("Start", "kr. 4000", "Få kr. 4000 når du passerer!"),
                new PropertyField("Rødovrevej", "Pris: kr. 1200", "Rødovrevej", 1200, Color.cyan),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Hvidovrevej", "Pris: kr. 1200", "Hvidovrevej", 1, Color.cyan),

                new FreeParkingField("Betal indkomstskat", "10% eller kr. 4000", "Betal plz"), // PLZ CHANGE THIS FIELD

                new PropertyField("SFL", "Pris: kr. 4000", "SFL Færgen", 4000, Color.gray),

                new PropertyField("Roskildevej", "Pris: kr. 2000", "Roskildevej", 2000, Color.pink),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Valby Langgade", "Pris: kr. 2400", "Valbylanggade", 2400, Color.pink),
                new PropertyField("Allégade", "Pris: kr. 2400", "Allégade", 2400, Color.pink),

                new JailField("Fængsel", "På besøg", "Enten er du endt i fængsel, ellers er du på besøg."),

                new PropertyField("Frederiksberg Allé", "Pris: kr. 2800", "Frederiksberg Allé", 2800, Color.green),

                new PropertyField("Tuborg", "Pris: kr. 3000", "Tuborg bryggeriet", 3000, Color.gray),

                new PropertyField("Bülowsvej", "Pris: kr. 2800", "Bülowsvej", 2800, Color.green),
                new PropertyField("Gl. Kongevej", "Pris: kr. 3200", "Gl. Kongevej", 3200, Color.green),

                new PropertyField("DSB", "Pris: kr. 4000", "DSB Færgen", 4000, Color.darkGray),

                new PropertyField("Bernstorffsvej", "Pris: kr. 3600", "Bernstorffsvej", 3600, Color.darkGray),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Hellerupvej", "Pris: kr. 3600", "Hellerupvej", 3600, Color.darkGray),
                new PropertyField("Strandvej", "Pris: kr. 4000", "Strandvej", 4000, Color.darkGray),

                new FreeParkingField("Fri", "Gratis Parkering", "Hold en fridag"),

                new PropertyField("Trianglen", "Pris: kr. 4400", "Trianglen", 4400, Color.red),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Østerbrogade", "Pris: kr. 4400", "Østerbrogade", 4400, Color.red),
                new PropertyField("Grønningen", "Pris: kr. 4800", "Grønningen", 4800, Color.red),

                new PropertyField("DFDS Seaways", "Pris: kr. 4000", "DFDS Seaways Færgen", 4000, Color.gray),

                new PropertyField("Bredgade", "Pris: kr. 5200", "Bredgade", 5200, Color.lightGray),
                new PropertyField("Kgs. Nytorv", "Pris: kr. 5200", "Kgs. Nytorv", 5200, Color.lightGray),

                new PropertyField("Coca Cola", "Pris: kr. 3000", "Coca Cola Bryggeriet", 3000, Color.darkGray),

                new PropertyField("Østergade", "Pris: kr. 5500", "Østergade", 5500, Color.lightGray),

                new ToJailField("Fængsel", "Gå til fængsel", "Øv, du skal til fængsel"),

                new PropertyField("Amagertorv", "Pris: kr. 6000", "Amagertorv", 6000, Color.yellow),
                new PropertyField("Vimmelskaftet", "Pris: kr. 6000", "Vimmelskaftet", 6000, Color.yellow),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Nygade", "Pris: kr. 6400", "Nygade", 6400, Color.yellow),

                new PropertyField("DSB", "Pris: kr. 4000", "DSB Færgen", 4000, Color.darkGray),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Frederiksberggade", "Pris: kr. 7000", "Frederiksberggade", 7000, Color.magenta),

                new FreeParkingField("Ekstraordinær statsskat", "betal kr. 2000", "Ekstraordinær statsskat"), // PLZ CHANGE THIS FIELD

                new PropertyField("Rådhuspladsen", "Pris: kr. 8000", "Rådhuspladsen", 8000, Color.magenta)
        };
    }
}
