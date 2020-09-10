package CoinFactoryTest;

import java.io.*;
import java.util.ArrayList;
// support encapsulation object to hold the choices list in memory and allow methods to access the data
public class ChoiceList implements Serializable {
    ArrayList<UserChoice> Results = new ArrayList<>();
    // arrayList was used because it is a dynamic list of choices where duplicate entries are possible

    public void add(UserChoice userChoice) {
        Results.add(userChoice);
    }

    public void PrintAllChoices() {
        int i=1;
        System.out.println("-----------------Results List--------------------");
        for ( UserChoice uc : Results )
        {
            System.out.println(uc.getResultStatement(i));
            i++;
        }
        System.out.println("-------------------------------------------------");
        System.out.println(" Finished printing "+(i-1)+" Results");
        System.out.println("-------------------------------------------------");
    }
}
