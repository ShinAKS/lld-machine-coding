package model;

import dao.ClientsDB;
import dao.PaymentModeDao;
import exception.ClientExistsException;
import exception.ClientNotFoundException;
import exception.InvalidOperationException;
import modes.ModeEnum;
import modes.PaymentModeFactory;
import modes.PaymentModeInterface;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ABCPaymentGateway implements PaymentGateway {

    private ClientsDB clientsDB;
    private PaymentModeDao paymentModeDao;
    private PaymentModeFactory paymentModeFactory;

    public ABCPaymentGateway(ClientsDB clientsDB, PaymentModeDao paymentModeDao) {
        this.clientsDB = clientsDB;
        this.paymentModeDao = paymentModeDao;
        this.paymentModeFactory = new PaymentModeFactory();
    }

    @Override
    public void addClient(String clientName) throws ClientExistsException {
        this.clientsDB.addClient(clientName);
    }

    @Override
    public void removeClient(String clientName) {
        this.clientsDB.removeClient(clientName);
    }

    @Override
    public boolean hasClient(String clientName) {
        return this.clientsDB.hasClient(clientName);
    }

    @Override
    public void showSupportedPaymentModes(Map<String,String> args) {

        String client = null;
        if (Objects.nonNull(args) && Objects.nonNull(args.get("client"))){
            client = args.get("client");
        }
        if (Objects.nonNull(client)){
            System.out.println("Available modes for client " + client + " : " + this.clientsDB.getAvailablePaymentModes(client));
            return;
        }
        System.out.println("Available modes : " +this.paymentModeDao.getAvailablePaymentModes());
    }

    public void addSupportForPaymentMode(String paymentMode){
        ModeEnum mode = ModeEnum.fromString(paymentMode);
        this.paymentModeDao.addPaymentMode(mode);
    }

    @Override
    public void addSupportForPaymentMode(String paymentMode, String clientName) throws ClientNotFoundException {
        ModeEnum mode = ModeEnum.fromString(paymentMode);
        addSupportForPaymentMode(paymentMode);
        this.clientsDB.addModeForClient(clientName, mode);
    }

    @Override
    public void removePayMode(String paymentMode) {
        ModeEnum mode = ModeEnum.fromString(paymentMode);
        this.paymentModeDao.removePaymentMode(mode);
        Set<String> clientsWithPaymentMode = this.clientsDB.getClientsWithPaymentMode(mode);
        for (String client : clientsWithPaymentMode) {
            this.clientsDB.removePaymentSupportForClient(client, mode);
        }
    }

    @Override
    public void makePayment(String clientName, ModeEnum modeEnum) throws InvalidOperationException {
        if (this.paymentModeDao.paymentModeExists(modeEnum)
                && this.clientsDB.getAllClients().contains(clientName)
                && this.clientsDB.getAvailablePaymentModes(clientName).contains(modeEnum)) {
            this.paymentModeFactory.getPaymentMode(modeEnum.name()).processPayment("");
        } else {
            throw new InvalidOperationException();
        }
    }
    public void showAllClientsAndSupports() {
        List<String> clients = this.clientsDB.getAllClients();
        for (String client : clients) {
            System.out.print(client + " : ");
            List<ModeEnum> modes = this.clientsDB.getAvailablePaymentModes(client).stream().toList();
            System.out.println(modes);
        }
    }
}
