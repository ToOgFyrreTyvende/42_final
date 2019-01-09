package Model.ChanceCards;

import java.awt.*;

public class CardFactory {
    public static ChanceCard[] makeCards(){
        return new ChanceCard[]{
                new MoveToCard("Ryk til start", "Start", 0),
                new PayBankCard("Du har spist for meget slik. BETAL 2M til baken", "Slik", 2),
                new OutOfJailCard("Du løslades uden omkostninger. \n" +
                        "Behold dette kort, indtil du får brug for det", "Fængsel"),
                new MoveToCard("Ryk frem til STRANDPROMMENADEN", "Strandprommenaden", 23),
                new GetPaidCard("Det er din fødselsdag! Alle giver dig 1M. TILLYKKE MED FØDSELSDAGEN!", "Fødselsdag", 1, true),
                new GetPaidCard("Du har lavet alle dine lektier. MODTAG 2M fra banken.", "Lektier", 2, false),
                /*new FreePropertyCard("GRATIS FELT! Ryk frem til et RØDT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", new Color(165,16,0)),*/

                /*new FreePropertyCard("GRATIS FELT! Ryk frem til et GRØNT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", new Color(111,209,111)),*/

                new FreePropertyCard("GRATIS FELT! Ryk frem til et TURKIS felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.cyan),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et PINK felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.pink),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et GRÅT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.lightGray),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et BLÅT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.white),

                /*new FreePropertyCard("GRATIS FELT! Ryk frem til et LILLA felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", new Color(134,4,190)),*/

                new FreePropertyCard("GRATIS FELT! Ryk frem til et GULT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.yellow)
        };
    }
}
