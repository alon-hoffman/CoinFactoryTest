package CoinTypes;

import CoinEnum.Coins;
import java.io.Serializable;
// data encapsulation class to standardize information transfer between classes
// to be used in factory method inside ops object
public class UserDataPkg implements Serializable {
    Coins fromCoin;
    Coins toCoin;
    private Double sumToConvert = 0.0;

    // override default constructor
    public UserDataPkg(Coins from, Coins to, double sum) {
        setFromCoin(from);
        setToCoin(to);
        setSumToConvert(sum);
    }

    private void setFromCoin(Coins fromCoin) {
        this.fromCoin = fromCoin;
    }

    private void setToCoin(Coins toCoin) {
        this.toCoin = toCoin;
    }

    private void setSumToConvert(Double sumToConvert) {
        this.sumToConvert = sumToConvert;
    }

    public Coins getFromCoin() {
        return fromCoin;
    }

    public Coins getToCoin() {
        return toCoin;
    }

    public Double getSumToConvert() {
        return sumToConvert;
    }
}