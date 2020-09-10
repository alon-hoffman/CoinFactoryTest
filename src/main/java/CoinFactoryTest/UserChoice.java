package CoinFactoryTest;

import CoinEnum.Coins;
import CoinTypes.UserDataPkg;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

// A support class for holding choices made for testing and data integrity checks
public class UserChoice implements Serializable {
    private double CalcResult = 0.0;
    private UserDataPkg udp;

    public UserChoice(UserDataPkg userData, double calcSum) {
            this.setCalcResult(calcSum);
            this.setUdp(userData);
    }
    // service methods to be used with the object
    //public UserDataPkg getUdp() { return udp; } // to be used only with permission checks
    public void setCalcResult(double calcResult) {
        CalcResult = calcResult;
    }
    public void setUdp(UserDataPkg udp) {
        this.udp = udp;
    }
    public double getCalcResult() {
        return CalcResult;
    }
    public Coins getUserCoinChoice () {
        return udp.getFromCoin();
    }
    public double getUserSumToConvert() {
        return udp.getSumToConvert();
    }
    public Coins getUserToCoin () { return udp.getToCoin();}

    public StringBuilder getResultStatement(int i) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Result ").append(i).append(" : ")
                .append(TruncDouble(getUserSumToConvert()))
                .append(getUserCoinChoice ())
                .append("--- Converts to : ")
                .append(TruncDouble(getCalcResult()))
                .append(getUserToCoin());
        return sb;
    }

    private String TruncDouble(double num)
    {
        DecimalFormat df = new DecimalFormat("#.##"); // visual trunc only - no rounding is actually saved to file
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(num); // returns a string representation of a double for screen use only
    }
}
