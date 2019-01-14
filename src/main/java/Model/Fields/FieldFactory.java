package Model.Fields;

import java.awt.*;

public class FieldFactory {

    public static Field[] makeFields() {

        return new Field[]{
                new StartField("Start", "kr. 4000", "Få kr. 4000 når du passerer!"),
                new PropertyField("Rødovrevej", "Pris: kr. 1200", "Rødovrevej", 1200, Color.cyan),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Hvidovrevej", "Pris: kr. 1200", "Hvidovrevej", 1200, Color.cyan),

                new TaxField("Betal indkomstskat", "10%/4000 kr.", "Betal enten 10% eller 4000 kr.",4000,true),

                new CompanyField("SFL", "Pris: kr. 4000", "SFL Færgen", 4000, new Color(57,101,221), true),

                new PropertyField("Roskildevej", "Pris: kr. 2000", "Roskildevej", 2000, Color.pink),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Valby Langgade", "Pris: kr. 2400", "Valbylanggade", 2400, Color.pink),
                new PropertyField("Allégade", "Pris: kr. 2400", "Allégade", 2400, Color.pink),

                new JailField("Fængsel", "På besøg", "Enten er du endt i fængsel, ellers er du på besøg."),

                new PropertyField("Frederiksberg Allé", "Pris: kr. 2800", "Frederiksberg Allé", 2800, new Color(111,209,111)),

                new CompanyField("Tuborg", "Pris: kr. 3000", "Tuborg bryggeriet", 3000, new Color(68,135,32), false),

                new PropertyField("Bülowsvej", "Pris: kr. 2800", "Bülowsvej", 2800, new Color(111,209,111)),
                new PropertyField("Gl. Kongevej", "Pris: kr. 3200", "Gl. Kongevej", 3200, new Color(111,209,111)),

                new CompanyField("DSB", "Pris: kr. 4000", "DSB Færgen", 4000, new Color(57,101,221), true),

                new PropertyField("Bernstorffsvej", "Pris: kr. 3600", "Bernstorffsvej", 3600, Color.lightGray),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Hellerupvej", "Pris: kr. 3600", "Hellerupvej", 3600, Color.lightGray),
                new PropertyField("Strandvej", "Pris: kr. 4000", "Strandvej", 4000, Color.lightGray),

                new FreeParkingField("Fri", "Gratis Parkering", "Hold en fridag"),

                new PropertyField("Trianglen", "Pris: kr. 4400", "Trianglen", 4400, Color.red),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Østerbrogade", "Pris: kr. 4400", "Østerbrogade", 4400, Color.red),
                new PropertyField("Grønningen", "Pris: kr. 4800", "Grønningen", 4800, Color.red),

                new CompanyField("DFDS Seaways", "Pris: kr. 4000", "DFDS Seaways Færgen", 4000, new Color(57,101,221), true),

                new PropertyField("Bredgade", "Pris: kr. 5200", "Bredgade", 5200, Color.white),
                new PropertyField("Kgs. Nytorv", "Pris: kr. 5200", "Kgs. Nytorv", 5200, Color.white),

                new CompanyField("Coca Cola", "Pris: kr. 3000", "Coca Cola Bryggeriet", 3000, new Color(220,43,25), false),

                new PropertyField("Østergade", "Pris: kr. 5500", "Østergade", 5500, Color.white),

                new ToJailField("Fængsel", "Gå til fængsel", "Øv, du skal til fængsel"),

                new PropertyField("Amagertorv", "Pris: kr. 6000", "Amagertorv", 6000, Color.yellow),
                new PropertyField("Vimmelskaftet", "Pris: kr. 6000", "Vimmelskaftet", 6000, Color.yellow),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Nygade", "Pris: kr. 6400", "Nygade", 6400, Color.yellow),

                new CompanyField("DSB", "Pris: kr. 4000", "DSB Færgen", 4000, new Color(57,101,221), true),

                new ChanceField("Chance", "Chance", "Tag et chancekort"),

                new PropertyField("Frederiksberggade", "Pris: kr. 7000", "Frederiksberggade", 7000, new Color(165, 90, 232)),

                new TaxField("Ekstraordinær statsskat", "betal kr. 2000", "Ekstraordinær statsskat",2000,false), // PLZ CHANGE THIS FIELD

                new PropertyField("Rådhuspladsen", "Pris: kr. 8000", "Rådhuspladsen", 8000, new Color(165, 90, 232))
        };
    }

    public static int[][] propertyPrices() {
        return new int[][]{
                // {Leje af grund, 1 hus, 2 huse, 3 huse, 4 huse, hotel, hus pris, hotel pris, pantsætningsværdi}
                // Rødovrevej
                {50, 250, 750, 2250, 4000, 6000, 1000, 5000, 600},
                // Hvidovrevej
                {50, 250, 750, 2250, 4000, 6000, 1000, 5000, 600},

                // Roskildevej
                {100, 600, 1800, 5400, 8000, 11000, 1000, 9000, 1000},
                // Valby Langgade
                {100, 600, 1800, 5400, 8000, 11000, 1000, 9000, 1000},
                // Allégade
                {150, 800, 2000, 6000, 9000, 12000, 1000, 10000, 1200},

                // Frederiksberg Allé
                {200, 1000, 3000, 9000, 12500, 15000, 2000, 14500, 1400},
                // Bülowsvej
                {200, 1000, 3000, 9000, 12500, 15000, 2000, 14500, 1400},
                // Gl. Kongevej
                {250, 1250, 3750, 10000, 14000, 18000, 2000, 16000, 1600},

                // Bernstorffsvej
                {300, 1400, 4000, 11000, 15000, 19000, 2000, 17000, 1800},
                // Hellerupvej
                {300, 1400, 4000, 11000, 15000, 19000, 2000, 17000, 1800},
                // Strandvejen
                {350, 1600, 4400, 12000, 16000, 20000, 2000, 18000, 2000},

                // Trianglen
                {350, 1800, 5000, 14000, 17500, 21000, 3000, 20500, 2200},
                // Østerbrogade
                {350, 1800, 5000, 14000, 17500, 21000, 3000, 20500, 2200},
                // Grønningen
                {400, 2000, 6000, 15000, 18500, 22000, 3000, 21500, 2400},

                // Bredgade
                {450, 2200, 6600, 16000, 19500, 23000, 3000, 22500, 2600},
                // Kgs. Nytorv
                {450, 2200, 6600, 16000, 19500, 23000, 3000, 22500, 2600},
                // Østergade
                {500, 2400, 7200, 17000, 20500, 24000, 3000, 23500, 2800},

                // Amagertorv
                {550, 2600, 7800, 18000, 22000, 25000, 4000, 26000, 3000},
                // Vimmelskaftet
                {550, 2600, 7800, 18000, 22000, 25000, 4000, 26000, 3000},
                // Nygade
                {600, 3000, 9000, 20000, 24000, 28000, 4000, 28000, 3200},

                // Frederiksberggade
                {700, 3500, 10000/*DENNE VÆRDI SKAL EFTERTJEKKES*/, 22000, 26000, 30000, 4000, 30000, 3500},
                // Rådhuspladsen
                {1000, 4000, 12000, 28000, 34000, 40000, 4000, 38000, 4000}
        };
    }
}
