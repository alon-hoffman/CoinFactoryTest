package CoinFactoryTest;

import CoinEnum.Coins;
// base coin class uses interface to declare methods. accepts value from derived classes
public abstract class Coin implements utils.iCalculate {

    private double Value= 0.0; // this is just a default Double value
    private Coins coinType; // no Default value for abstract class

    public void setValue(double newV) { // assuming REST API update at instantiation - must be a Set method to prevent access to a variable directly
        this.Value=newV;
    }
    public void setCoinType(Coins ct){
        this.coinType=ct;
    }

    public double getValue() {
        return this.Value;
    }

    public Coins getCoinType() {
        return coinType;
    }

    @Override
    public double calculate(double sum) {
        return sum * this.getValue();
    }
}
