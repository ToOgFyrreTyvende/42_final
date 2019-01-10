package Model.ChanceCards;

import java.awt.*;

public class CardFactory {
    public static ChanceCard[] makeCards(){
        return new ChanceCard[]{
                new MoveToCard("Ryk frem til start", "Start", 0),
                new MoveToCard("Ryk frem til start", "Start", 0),

                new GetPaidCard("Modtag udbytte af Deres aktier - kr. 1.000", "Aktier", 1000, true),
                new GetPaidCard("De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken", "Aktier", 1000, true),
                new GetPaidCard("Modtag udbytte af Deres aktier - kr. 1.000.", "Aktier", 1000, true),
                new GetPaidCard("Kommunen har eftergivet er kvartals skat. Hæv i banken kr. 3.000.", "Kvartals skat", 3000, true),
                new GetPaidCard("De havde en række med elleve rigtige i tipning. Modtag kr. 1.000.", "Tipning", 1000, true),
                new GetPaidCard("Deres præmieobligation er udtrukket. De modtager kr. 1.000 af banken.", "Præmieobligation", 1000, true),
                new GetPaidCard("Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken.", "Egen avl", 200, true),
                new GetPaidCard("De har solgt nogle gamle møbler på auktion. Modtag kr. 1.000 af banken", "Møbler", 1000, true),
                new GetPaidCard("De har vundet i Klasselotteriet. Modtag kr. 500.", "Klasselotteriet", 500, true),
                new GetPaidCard("Deres præmieobligation er udtrukket. De modtager kr. 1.000 af banken.", "Præmieobligation", 1000, true),
                new GetPaidCard("Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000.", "Gageforhøjelse", 1000, true),
                new GetPaidCard("De har vundet i Klasselotteriet. Modtag kr. 500.", "Klasselotteriet", 500, true),

                new PayBankCard("Betal kr. 3.000 for reparation af Deres Vogn", "Vogn", 3000),
                new PayBankCard("Betal kr. 200 for levering af 2 kasser øl.", "Levering", 200),
                new PayBankCard("De har modtaget Deres tandlægeregning. Betal kr. 2.000", "Tandlægeregning", 2000),
                new PayBankCard("De har fået en parkeringsbøde. Betal kr. 200 i bøde.", "Parkeringsbøde", 200),
                new PayBankCard("Betal Deres bilforsikring - kr. 1.000.", "Bilforsikring", 1000),
                new PayBankCard("Betal kr. 3.000 for reparation af Deres Vogn", "Vogn", 3000),
                new PayBankCard("De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200.", "Told", 200),
                new PayBankCard("De har købt 4 nye dæk til Deres vogn. Betal kr. 1.000.", "Dæk", 1000),
                new PayBankCard("Betal for vognvask og smøring kr. 300.", "Vognvask", 300),
                new PayBankCard("De har kørt frem for FULDT STOP. Betal kr. 1.000 i bøde.", "Fuldt stop", 1000),
/*
                // Kender ikke index for de forskellige pladser... Og hvis man rykker i fængsel, modtager man ikke 4000kr.
                new MoveToCard("Ryk frem til Rådhuspladsen", "Rådhuspladsen", ??),
                new MoveToCard("Ryk frem til Grønningen. Hvis De passerer START, indkassér da kr. 4.000", "Grønningen", ??),
                new MoveToCard("Tag med Mols-Linien. Flyt brikken frem, og, hvis De passerer START, indkassér da kr. 4.000", "Mols-Linien", ??),
                new MoveToCard("Tag med den nærmeste færge. Flyt brikken frem, og hvis De passerer START indkassér da kr. 4.000.", "Færge", ??),
                new MoveToCard("Ryk frem til Vimmelskaftet. Hvis De passerer START, indkassér da kr. 4.000", "Vimmelskaftet", ??),
                new MoveToCard("Ryk frem til Strandvejen. Hvis De passerer START, indkassér da kr. 4.000", "Strandvejen", ??),
                new MoveToCard("Ryk frem til Frederiksberg Allé. Hvis De passerer START, indkassér da kr. 4.000", "Frederiksberg Allé", ??),

                new MoveToCard("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4.000.", "Fængsel", ??),
                new MoveToCard("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4.000.", "Fængsel", ??),
*/

                new OutOfJailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. \n" +
                        "Dette kort kan opbevares, undtil De får brug for det, eller De kan sælge det.", "Fængsel"),
                new OutOfJailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. \n" +
                        "Dette kort kan opbevares, undtil De får brug for det, eller De kan sælge det.", "Fængsel"),

        };
    }
}
