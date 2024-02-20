package modes;

import modes.impl.CardPaymentMode;
import modes.impl.NetbankingPaymentMode;
import modes.impl.UPIPaymentMode;

import java.util.HashMap;
import java.util.Map;

public class PaymentModeFactory {

    Map<ModeEnum,PaymentModeInterface> paymentModeInterfaceMap;

    public PaymentModeFactory(){
        this.paymentModeInterfaceMap = new HashMap<>();
        this.paymentModeInterfaceMap.put(ModeEnum.UPI,new UPIPaymentMode());
        this.paymentModeInterfaceMap.put(ModeEnum.CARD,new CardPaymentMode());
        this.paymentModeInterfaceMap.put(ModeEnum.NETBANKING,new NetbankingPaymentMode());
    }

    public PaymentModeInterface getPaymentMode(String mode){

        ModeEnum modeEnum = ModeEnum.fromString(mode);
        return this.paymentModeInterfaceMap.get(modeEnum);
    }
}
