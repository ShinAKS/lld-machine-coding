package model;

import exception.ClientExistsException;
import exception.ClientNotFoundException;
import exception.InvalidOperationException;
import modes.ModeEnum;

import java.util.Map;

public interface PaymentGateway {

    void addClient(String clientName) throws ClientExistsException;

    void removeClient(String clientName);

    boolean hasClient(String clientName);

    void showSupportedPaymentModes(Map<String,String> args);

    void addSupportForPaymentMode(String paymentMode);

    void addSupportForPaymentMode(String paymentMode, String clientName) throws ClientNotFoundException;

    void removePayMode(String paymentMode);

    void makePayment(String client, ModeEnum modeEnum) throws InvalidOperationException;

    void showAllClientsAndSupports();
}
