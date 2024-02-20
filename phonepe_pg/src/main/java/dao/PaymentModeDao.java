package dao;

import modes.ModeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PaymentModeDao {

    public static PaymentModeDao INSTANCE;

//    mode -> [BANK NAME, transactions]
//           ->
    Map<ModeEnum, Map<String,Integer>> modeVsBankStatsMap;

    private PaymentModeDao(){
        this.modeVsBankStatsMap = new HashMap<>();
    }

    public static PaymentModeDao getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PaymentModeDao();
        }
        return INSTANCE;
    }

    public void addPaymentMode(ModeEnum modeEnum){
        if (this.modeVsBankStatsMap.containsKey(modeEnum))return;
        this.modeVsBankStatsMap.put(modeEnum,new HashMap<>());
    }

    public void addBankForPaymentMode(ModeEnum mode, String bankName){
        Map<String,Integer> bankDetails = modeVsBankStatsMap.getOrDefault(mode,new HashMap<>());
        bankDetails.put(bankName,bankDetails.getOrDefault(bankName,0));
        this.modeVsBankStatsMap.put(mode,bankDetails);
    }

    public List<String> getAvailableBanksForPaymentMode(ModeEnum mode){
        return modeVsBankStatsMap.get(mode)
                .keySet()
                .stream()
                .toList();
    }

    public void makePaymentForModeWithBank(ModeEnum mode, String bankName){
        Map<String,Integer> bankDetails = modeVsBankStatsMap.get(mode);
        int paymentsDoneAlready = bankDetails.get(bankName);
        bankDetails.put(bankName,paymentsDoneAlready + 1);
        modeVsBankStatsMap.put(mode,bankDetails);
    }

    public void showDistributionForMode(ModeEnum mode){
        System.out.println("Mode : " + mode.name());
        Map<String,Integer> bankDetails = modeVsBankStatsMap.get(mode);
        if (Objects.isNull(bankDetails)){
            System.out.println("Mode : " + mode +" doesn't exist");
            return;
        }
        for (String bank : bankDetails.keySet()){
            int currentTransactions = bankDetails.get(bank);
            System.out.println("Bank : " + bank + " , Transactions: " + currentTransactions);
        }
    }

    public void removePaymentMode(ModeEnum mode){
        this.modeVsBankStatsMap.remove(mode);
    }

    public boolean paymentModeExists(ModeEnum mode){
        if (this.modeVsBankStatsMap.containsKey(mode)){
            return true;
        }
        return false;
    }

    public List<ModeEnum> getAvailablePaymentModes(){
        return this.modeVsBankStatsMap.keySet().stream().toList();
    }

}
