package utils;
import CoinEnum.Coins;
// enum representation of the ISO 4217 money symbols - codes found on Wikipedia.
public class CoinFactory {

    public CoinFactoryTest.Coin Create(Coins coinType)
    {
        return switch (coinType) {
            case ILS -> new CoinTypes.ILS();
            case USD -> new CoinTypes.USD();
            default -> null;
        };
    }
}

