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
                new FreePropertyCard("GRATIS FELT! Ryk frem til et GRÅT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.gray),
                new FreePropertyCard("GRATIS FELT! Ryk frem til et BLÅT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.blue),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et PINK felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.pink),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et GULT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.yellow),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et RØDT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.red),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et ORANGE felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.orange),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et GRØNT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.green),

                new FreePropertyCard("GRATIS FELT! Ryk frem til et TURKIS felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.cyan)
        };
    }
}
