package utils;

import CoinEnum.Coins;
import CoinFactoryTest.*;
import CoinTypes.UserDataPkg;
import java.io.*;
import java.util.Scanner;
// support operational class
public class ConsoleOps {
    boolean dbgMsg = true; // Sysadmin dbgMsg switch on/off

    //  -------------------------------------Screens-----------------------------------------------------------------------
public UserDataPkg runWelcomeScreen (boolean firstRun) {

    Coins CoinChoice;
    Coins CoinTo;
    double sumToConvert;

    //First screen (Welcome Screen)
    say(firstRun,"--- ----------------------------- ---");
    say(firstRun,"--- Welcome to currency converter ---");
    say(firstRun,"--- ----------------------------- ---");

    do {
        say(true, "Please choose a Coin to Convert FROM (1/2):");
        CoinChoice = readUserCoinChoice();
        //Coins CoinTo = readUserCoinChoice(); once other coins are added - a second input question will be required
        if (CoinChoice==Coins.Unknown)
        {say (true,"---> Invalid Choice! ---> plz try again if its not to hard for you");}

    } while (CoinChoice == Coins.Unknown);

    sumToConvert = this.readUserSUM();

    say(dbgMsg, " you entered "+sumToConvert+""+CoinChoice);

    if (CoinChoice==Coins.ILS)  // once other coins are added - replace with nested if or switch or use second question
        {CoinTo=Coins.USD;}
    else
        {CoinTo = Coins.ILS;}

    return CreateUserDataObj(CoinChoice,CoinTo,sumToConvert); // user data is safe and sent after validations
}

    public Coins readUserCoinChoice () {
        boolean RepeatQuestion;
        int choice = 0;
        do {
            say(true, "1. Dollars to Shekels ---or---- 2. Shekels to Dollars");
            say(true, "type your choice (Integer 1 or 2) followed by ENTER : ");

            try {
                Scanner sc = new Scanner(System.in);
                choice = sc.nextInt();
                RepeatQuestion = false;

            } catch (Exception e) {
                say(true, "---> which part of (Int only) was not clear??");
                RepeatQuestion = true;
            }
        } while (RepeatQuestion);

        return switch (choice) {
            case 1 -> Coins.ILS;
            case 2 -> Coins.USD;
            default -> Coins.Unknown;
        };
    }

    public double readUserSUM () {
        do {
            say(true,"Enter Sum to Convert (in Double)");
            try {
                Scanner sc = new Scanner(System.in);
                return sc.nextDouble();
            } catch (Exception e) {
                say(true, "---> which part of (Double only) was not clear??");
            }
        } while (true);
    }

    //public void ShowResultToScreen(double result, Coins coinType) {
    //        say(true, new StringBuilder().append("Result : ").append(result).append(coinType).toString());
    //    }

    public boolean getContinueChoice() {

        do {
            StringBuilder answer = new StringBuilder();
            say(true, "Continue?:(y/n)");

            Scanner sc = new Scanner(System.in);
            answer.append(sc.next());

            say(false, "DBGMSG: User Answer :"+answer.toString().toLowerCase());

            if (answer.toString().toLowerCase().equalsIgnoreCase("n")) {
                    return false;
            } else {
                if (answer.toString().toLowerCase().equalsIgnoreCase("y")){
                    return true;
                } else { // all other input text is considered as wrong typed text
                    say(true, "----> (y/n) only ----> plz try again and this time concentrate real Hard....");
                }
            }
        // repeats until return is performed upon proper answers
        } while (true);
    }

    public void runEndScreen () {
        say(true, "---- --------------------------------------- ----");
        say(true, "---- Thanks for using our currency converter ----");
        say(true, "---- --------------------------------------- ----");
        // some end code will go here
    }

    //------------------------------------------utility methods--------------------------------------------------------




    public void say(Boolean showMsg,String text){
        if (showMsg) { System.out.println(text);}
    }

    public void SerializeCoin (Coin SomeCoin) {

        String FileName = "CoinFile.ser"; // SysAdmin will edit this field to match actual path and file

        try {
            FileOutputStream f = new FileOutputStream(FileName);
            ObjectOutputStream outStream = new ObjectOutputStream(f);
            outStream.writeObject(SomeCoin);
            outStream.close();

            this.say(dbgMsg,"Saved Coin to file : "+ FileName+" With Value : "+SomeCoin.getValue());

        }   catch (IOException e) {
            e.printStackTrace();}
    }
    public void SerializeChoices (ChoiceList choices,String FileName) {
        // object accepts filename from calling method
        //String FileName = "ChoicesFile.ser";

        try {
            FileOutputStream f = new FileOutputStream(FileName);
            ObjectOutputStream outStream = new ObjectOutputStream(f);
            outStream.writeObject(choices);
            outStream.close();
        }   catch (IOException e) { e.printStackTrace();}
    }

    public ChoiceList readChoicesFromFile (String FileName) {

        //String fName = "ChoicesFile.ser";

        try {
            FileInputStream f = new FileInputStream(FileName);
            ObjectInputStream in = new ObjectInputStream(f);
            ChoiceList TempList = (ChoiceList)in.readObject();
            in.close();
            return TempList;

        }   catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Coin CreateCoin (Coins cType) { // Coin Factory method uses Factory object
            CoinFactory CF = new CoinFactory();
            this.say(dbgMsg,"Created A Coin Object using a factory method of type : "+cType);
            return CF.Create(cType);
    }

    public UserDataPkg CreateUserDataObj (Coins fC, Coins tC, double sum) { // userdata factory method
            return new UserDataPkg(fC,tC,sum); // get actual data from User Screen
    }
}
