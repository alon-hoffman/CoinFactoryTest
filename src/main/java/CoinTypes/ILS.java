package CoinTypes;

import CoinEnum.Coins;
import java.io.Serializable;

public class ILS extends CoinFactoryTest.Coin implements Serializable {

    //Default constructor
    public ILS() {
        super.setValue(0.28); // get up to date value from REST API
        super.setCoinType(Coins.ILS);
    }

    @Override
    public void setValue(double newV) {
        super.setValue(newV);
    }

    @Override
    public double getValue() {
        return super.getValue();
    }

    @Override
    public Coins getCoinType() {
        return super.getCoinType();
    }

    @Override
    public double calculate(double sum) {
        return super.calculate(sum);
    }
}
