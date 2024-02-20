//import dao.ClientsDB;
//import dao.PaymentModeDao;
//import exception.ClientExistsException;
//import exception.ClientNotFoundException;
//import exception.InvalidOperationException;
//import model.ABCPaymentGateway;
//import model.PaymentGateway;
//import modes.ModeEnum;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//
//
//    public static void main(String[] args) throws ClientExistsException, ClientNotFoundException {
//
//        ClientsDB clientsDB = ClientsDB.getInstance();
//        PaymentModeDao paymentModeDao = PaymentModeDao.getInstance();
//        PaymentGateway paymentGateway = new ABCPaymentGateway(clientsDB,paymentModeDao);
//
//        paymentGateway.addClient("FLIPKART");
//        System.out.println(paymentGateway.hasClient("FLIPKART"));
//        System.out.println(paymentGateway.hasClient("MYNTRA"));
////        paymentGateway.removeClient("FLIPKART");
//        System.out.println(paymentGateway.hasClient("FLIPKART"));
//        try {
//            paymentGateway.addSupportForPaymentMode("UPI","FLIPKART");
//        } catch (ClientNotFoundException e) {
//            System.out.println("Client wasn't found");
//        }
//
////        Map<String,String> arg = new HashMap<>();
////        arg.put("client","MYNTRA");
////        paymentGateway.removePayMode("UPI");
////        paymentGateway.showSupportedPaymentModes(arg);
////
//        paymentModeDao.addBankForPaymentMode(ModeEnum.UPI,"HDFC");
//        paymentModeDao.addBankForPaymentMode(ModeEnum.UPI,"ICICI");
//
//
//        try {
//
//            paymentGateway.makePayment("FLIPKART",ModeEnum.UPI);
//
//            paymentGateway.makePayment("FLIPKART",ModeEnum.UPI);
//            paymentGateway.makePayment("FLIPKART",ModeEnum.UPI);
//            paymentGateway.makePayment("FLIPKART",ModeEnum.UPI);
//            paymentGateway.makePayment("FLIPKART",ModeEnum.UPI);
//        } catch (InvalidOperationException e) {
//            System.out.println("Either client doesn't exist or else method is invalid for client");
//        }
//
//        paymentModeDao.showDistributionForMode(ModeEnum.UPI);
////
////
////        try {
////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
////        } catch (InvalidOperationException e) {
////            System.out.println("Either client doesn't exist or else method is invalid for client");
////        }
////
////
////        paymentModeDao.showDistributionForMode(ModeEnum.CARD);
////
////        try {
////            paymentGateway.addSupportForPaymentMode("CARD","FLIPKART");
////        } catch (ClientNotFoundException e) {
////            System.out.println("Client not found : " );
////        }
////
////        paymentModeDao.addBankForPaymentMode(ModeEnum.CARD,"NATWEST");
////
////        try {
////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
////        } catch (InvalidOperationException e) {
////            System.out.println("Either client doesn't exist or else method is invalid for client");
////        }
////
////        paymentModeDao.showDistributionForMode(ModeEnum.CARD);
////
////        paymentGateway.removePayMode("CARD");
////
////        try {
////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
////        } catch (InvalidOperationException e) {
////            System.out.println("Either client doesn't exist or else method is invalid for client");
////        }
////
////        paymentModeDao.showDistributionForMode(ModeEnum.CARD);
//////
////        paymentGateway.showAllClientsAndSupports();
////
////        paymentGateway.addClient("MYNTRA");
////        paymentGateway.addSupportForPaymentMode("CARD","MYNTRA");
////        try {
////            paymentGateway.addSupportForPaymentMode("CARD","MYNTRA");
////        } catch (ClientNotFoundException e) {
////            e.printStackTrace();
////        }
////
////        Map<String,String> argsMap = new HashMap<>();
////        paymentGateway.showSupportedPaymentModes(argsMap);
//////
//////        paymentModeDao.addBankForPaymentMode(ModeEnum.CARD,"SOFTBANK");
//////
//////        try {
//////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
//////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
//////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
//////        } catch (InvalidOperationException e) {
//////            System.out.println("Either client doesn't exist or else method is invalid for client");
//////        }
//////
//////        paymentModeDao.showDistributionForMode(ModeEnum.CARD);
////
////
//////        paymentGateway.removeClient("FLIPKART");
//////        try {
//////            paymentGateway.makePayment("FLIPKART",ModeEnum.CARD);
//////        } catch (InvalidOperationException e) {
//////            System.out.println("Either client doesn't exist or else method is invalid for client");
//////        }
//    }
//}

class Main{
    public static void main(String args[]) {


        Thread t1 = new OddTask(10);
        Thread t2 = new EvenTask(10);
        t1.start();
        t2.start();
    }
}

class OddTask extends Thread {

    private int n;
    private int current = 1;
    public OddTask(int n){
        this.n = n;
    }

    public void run(){
        while (current <= this.n){
            if (current%2 == 0)
                System.out.println(current);
            current++;
        }
    }
}

class EvenTask extends Thread {

    private int n;
    private int current = 2;
    public EvenTask(int n){
        this.n = n;
    }

    public void run(){
        while (current <= this.n){
            if (current%2==0)
                System.out.println(current);
            current++;
        }
    }
}