package Model.Kort;

import java.awt.*;

public class KortFabrik {
    public static ChanceKort[] lavKort(){
        return new ChanceKort[]{
                new RykKort("Ryk til start", "Start", 0),
                new BetalKort("Du har spist for meget slik. BETAL 2M til baken", "Slik", 2),
                new FaengselKort("Du løslades uden omkostninger. \n" +
                        "Behold dette kort, indtil du får brug for det", "Fængsel"),
                new RykKort("Ryk frem til STRANDPROMMENADEN", "Strandprommenaden", 23),
                new BlivBetaltKort("Det er din fødselsdag! Alle giver dig 1M. TILLYKKE MED FØDSELSDAGEN!", "Fødselsdag", 1, true),
                new BlivBetaltKort("Du har lavet alle dine lektier. MODTAG 2M fra banken.", "Lektier", 2, false),
                new GratisFeltKort("GRATIS FELT! Ryk frem til et GRÅT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.gray),
                new GratisFeltKort("GRATIS FELT! Ryk frem til et BLÅT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.blue),

                new GratisFeltKort("GRATIS FELT! Ryk frem til et PINK felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.pink),

                new GratisFeltKort("GRATIS FELT! Ryk frem til et GULT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.yellow),

                new GratisFeltKort("GRATIS FELT! Ryk frem til et RØDT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.red),

                new GratisFeltKort("GRATIS FELT! Ryk frem til et ORANGE felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.orange),

                new GratisFeltKort("GRATIS FELT! Ryk frem til et GRØNT felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.green),

                new GratisFeltKort("GRATIS FELT! Ryk frem til et TURKIS felt. " +
                        "Hvis det er ledig, får du det GRATIS! Ellers skal du BETALE leje til ejeren",
                        "GUL", Color.cyan)
        };
    }
}
