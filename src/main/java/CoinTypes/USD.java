package CoinTypes;

import CoinEnum.Coins;
import java.io.Serializable;

public class USD extends CoinFactoryTest.Coin implements Serializable {

    //Default constructor
    public USD() {
        super.setValue(3.52); // get value from REST API
        super.setCoinType(Coins.USD);
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