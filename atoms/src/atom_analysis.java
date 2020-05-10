
import java.util.*;
public class atom_analysis {
    static Scanner In = new Scanner(System.in);

    static int findLevel (int level){
        return (int)(2*(Math.pow(level,2)));
    }
    private static void buildAtom(int[] energyLevels ,int protons, int neutrons){
        String lines = " ";
        String lines2 = " ";
        String line2 = " ";
        int counter = 0;

        for(int pro =protons;pro>0;pro/=10)
            counter++;
        for(int space = 0; space < 8-counter; space++)
            lines2 +=" ";
        lines2 += "|";
        lines += "         |";
        counter = 0;
        for(int x=neutrons;x>0;x/=10)
            counter++;
        for(int space = 0; space < 8-counter; space++)
            line2+=" ";
        line2 +="|"+energyLevels[0];

        for(int i = 1;i<energyLevels.length&&energyLevels[i]!= 0;i++){
            lines += "         |";
            lines2 += "         |";
            counter = 0;
            for(int x=energyLevels[i-1];x>0;x/=10)
                counter++;
            for(int space = 0; space < 9-counter; space++)
                line2+=" ";

            line2 +="|"+energyLevels[i];
        }
        System.out.println(" "+lines);
        System.out.println(" "+protons+"P"+lines2);
        System.out.println(" "+neutrons+"N"+line2);
        System.out.println(" "+lines);
    }



    public static void main(String[]args) {
        System.out.print(" atomic number: ");
        int protons = In.nextInt();
        while (protons <= 0 || protons > 118) {
            System.out.println(" atom not exist");
            System.out.print(" atomic number: ");
            protons = In.nextInt();
        }
        System.out.print(" atomic mass: ");
        int neutrons = (int)Math.round(Math.abs(In.nextDouble()) - protons);
        while (neutrons < 0) {
            System.out.println(" neutrons number can't be negative");
            System.out.print(" neutrons: ");
            neutrons = In.nextInt();
        }
        System.out.print(" electrical charge: ");
        int electrons = protons - In.nextInt();
        if (electrons < 0)
            electrons = 0;

        int El = 1;
        int[] EnergyLevels = new int[7];
        int levelIn;
        levelIn = 1;

        for(int i = electrons; i > 0;i = i- findLevel(El),El++){
            for (int level = EnergyLevels[El-levelIn]; level<findLevel(El) && level<i;level++){
                if(El >= 3 && El<7){
                    if(EnergyLevels[El-1] % 8 == 0 && EnergyLevels[El-1]!= 0){
                        levelIn = 0;
                        if(EnergyLevels[El]%2 == 0 && EnergyLevels[El] != 0)
                            levelIn = 1;
                    }
                }
                EnergyLevels[El-levelIn]++;
            }
        }
        System.out.println("");
        System.out.println(" your atom:");
        buildAtom(EnergyLevels,protons,neutrons);

        System.out.println("");
        String line = " Ion:";

        //Ion
        if(electrons==protons)
            line += " neutral";
        else{
            if(electrons>protons)
                line += " negative: -" + (electrons-protons);
            else
                line += " positive:+"+(-1*(electrons-protons));
        }

        //other Data
        System.out.println(" more info:");
        System.out.println(line+"("+electrons+"e)");
        AtomReader.printAtom(protons);
        System.out.println("");
        System.out.println(" --press any key + enter to exit--");
        In.next();
        System.exit(0);
    }
}
