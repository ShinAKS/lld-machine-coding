package modes;

import exception.InvalidOperationException;

public interface PaymentModeInterface {

    boolean validate(String arguments);

    void processPayment(String arguments) throws InvalidOperationException;

}
