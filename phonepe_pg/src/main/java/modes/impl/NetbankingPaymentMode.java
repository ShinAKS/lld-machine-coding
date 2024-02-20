package modes.impl;

import dao.PaymentModeDao;
import exception.InvalidOperationException;
import modes.ModeEnum;
import modes.PaymentModeInterface;

import java.util.List;
import java.util.Random;

public class NetbankingPaymentMode implements PaymentModeInterface {

    private PaymentModeDao paymentModeDao;

    private ModeEnum mode;

    public NetbankingPaymentMode(){
        this.mode = ModeEnum.CARD;
        this.paymentModeDao = PaymentModeDao.getInstance();
    }
    @Override
    public boolean validate(String arguments) {
        return true;
    }

    @Override
    public void processPayment(String arguments) throws InvalidOperationException {

        if (!validate(arguments)){
            throw new InvalidOperationException();
        }

        List<String> supportedBanks = paymentModeDao.getAvailableBanksForPaymentMode(this.mode);

        Random ran = new Random();

        int bankIndexForTransaction = ran.nextInt(supportedBanks.size());

        paymentModeDao.makePaymentForModeWithBank(this.mode, supportedBanks.get(bankIndexForTransaction));
    }
}
