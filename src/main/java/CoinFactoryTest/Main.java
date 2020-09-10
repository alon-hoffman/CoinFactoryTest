package CoinFactoryTest;

import CoinEnum.Coins;
import CoinTypes.UserDataPkg;
import utils.ConsoleOps;

public class Main {
//
        /**
         * Program performs sum conversions between different coins.
         * first part of the main Class is a testing sequence to check the objects of the program.
         * The actual program starts after the label : "main program accepting user choices".
         * @param args main args
         * cOps object encapsulates access to console and service methods.
         * other JAVADOC Descriptions and params go here (to be edited at later time)
         */
    public static void main(String[] args) {
            //---------------------------------------- utility vars ---------------------------------------------------
            ConsoleOps cOps = new ConsoleOps();                         // Utility object to run services to main
            Boolean DbgMsg = false;                                     // Debug messages switch show/hide
            Boolean runTests = false;                                   // Debug testing objects on/off
            //---------------------------------------------------------------------------------------------------------
            if (runTests) {
                cOps.say(DbgMsg, "Hello using cOps object");
                cOps.say(DbgMsg, "----- Testing objects Construction -----");
                CoinTypes.ILS shekel = new CoinTypes.ILS();                 // replaced with coin factory and abstract Coin Class
                CoinTypes.USD Dollar = new CoinTypes.USD();                 // replaced with coin factory and abstract Coin Class

                cOps.say(DbgMsg, "Shekel Value : " + shekel.getValue());  // Showing the default value
                shekel.setValue(0.29);                                      // setting a new value - To be used with REST API
                cOps.say(DbgMsg, "Updated Value in super class : " + shekel.getValue());    // Showing updated value
                cOps.say(DbgMsg, "Dollar Value : " + Dollar.getValue());

                cOps.say(DbgMsg, "----- testing Calculate function from abstract object Coin -----");
                cOps.say(DbgMsg, "Shekel Calc (sum=100): " + shekel.calculate(100));
                cOps.say(DbgMsg, "Dollar Calc (sum=100): " + Dollar.calculate(100));
                // using a factory method from a factory object

                // legacy : creation of a factory class is not required in the main and a utils class is used instead
                cOps.say(DbgMsg, "-----Creating Coin Objects using a factory method :-----");
                Coin newS = cOps.CreateCoin(Coins.ILS);                     // User Choice is used to create the requested coin
                Coin newD = cOps.CreateCoin(Coins.USD);                     // Using Create (factory) Method from ConsoleOps Object

                cOps.say(DbgMsg, "----- Testing Calculate function with the new objects created in factory method ------");
                cOps.say(DbgMsg, "Shekel Calc with newS (sum=10):" + newS.calculate(10) + " CoinType : " + newS.getCoinType());
                cOps.say(DbgMsg, "Dollar Calc with newD (sum=10):" + newD.calculate(10) + " CoinType : " + newD.getCoinType());
                cOps.say(DbgMsg, "----- Notice that each created coin is of type Coin (the abstract) rather then Specific classes ILS or USD------");

                cOps.SerializeCoin(newS);
                cOps.SerializeCoin(newD);

                // emulate user Data
                System.out.println();
                UserDataPkg udp = cOps.CreateUserDataObj(Coins.USD, Coins.ILS, 125.14); // emulates input of user data
                //new UserDataPkg(Coins.USD,Coins.ILS,125.14);
                // user will enter unknown values and they need to be tested on input
                Coin newUserCoin = cOps.CreateCoin(udp.getFromCoin());

                cOps.say(DbgMsg, "Calc with newUserCoin (sum=" + udp.getSumToConvert() + udp.getFromCoin()
                        + " Converts to " + newUserCoin.calculate(udp.getSumToConvert()) + udp.getToCoin() + ")");
            } // end test sequence

            //---------------------------------------------------------------------------------------------------------
            // main program accepting user choices
            // Vars:
            boolean runFirstTime = true;
            ChoiceList lstResults = new ChoiceList();     // object to hold a list of choices and calc results
            final String ChoicesFileName = "ChoicesFile.ser"; // file name to save the results list
            //------------------------------------------Main Loop------------------------------------------------------
            do {
                    UserDataPkg uData = cOps.runWelcomeScreen(runFirstTime); // standard data pkg to hold user choices
                    runFirstTime = false; // used in the object loop to remove start text after first try
                    Coin c = cOps.CreateCoin(uData.getFromCoin()); // holds the selected values

                    lstResults.add(new UserChoice(uData,c.calculate(uData.getSumToConvert())));
                    // adds result to A list of results - can hold duplicate entries

            } while (cOps.getContinueChoice()); // returns boolean value - proceed y/n
            //-----------------------------------------after Loop------------------------------------------------------
            cOps.runEndScreen();                                        // final text and finishing protocol
            lstResults.PrintAllChoices();                               // printing results from memory object
            cOps.SerializeChoices(lstResults,ChoicesFileName);          // Saving Results to ChoicesFile

            // 6. Once user decided to exit (choose n in result screen), open results file
            // automatically (from code).
            ChoiceList newListFromFile = cOps.readChoicesFromFile(ChoicesFileName);
            cOps.say(true,"=> Printing List from File : "+ChoicesFileName);
            newListFromFile.PrintAllChoices();
            //--------------------------------------end main program---------------------------------------------------
    }
}
