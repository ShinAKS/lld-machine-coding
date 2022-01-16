package com.ayush.service;

import com.ayush.dao.UserDao;
import com.ayush.exceptions.AccountNotFoundException;
import com.ayush.exceptions.UserNotFoundException;
import com.ayush.model.Account;
import com.ayush.model.Lumpsum;
import com.ayush.model.User;

import java.util.Optional;

public class UserService implements IUserService{

    private UserDao userDao;

    public UserService(){
        this.userDao = UserDao.getInstance();
    }

    @Override
    public void addLoan(String bankName, String userName, double principal, double time, double rate) {
        try{
            User user = userDao.getUser(userName);
            Account account = new Account(bankName,principal,rate,time);
            user.getAccountList().add(account);
        }catch(UserNotFoundException e){
            User user = new User(userName);
            Account account = new Account(bankName,principal,rate,time);
            user.getAccountList().add(account);
            userDao.addUser(user);
        }
        return;
    }

    @Override
    public void addPayment(String bankName, String userName, double amountPaid, int emiNo) {
        try{
            User user = userDao.getUser(userName);
            Optional<Account> foundAccount = getAccountForUser(user,bankName);
            if (!foundAccount.isPresent()){
                throw new AccountNotFoundException("Account not found for the user");
            }
            Account userAccount = foundAccount.get();
            Lumpsum lumpsum = new Lumpsum(emiNo,amountPaid);

            Lumpsum closestEmi = userAccount.getPaidAmounts().lower(lumpsum);
            if (closestEmi!=null ){
                if (closestEmi.getEmiNo()==emiNo)
                    closestEmi.setAmount(closestEmi.getAmount() + amountPaid);
                else {
                    lumpsum.setAmount(amountPaid+closestEmi.getAmount());
                    userAccount.getPaidAmounts().add(lumpsum);
                }
            }
            else{
                userAccount.getPaidAmounts().add(lumpsum);
            }
        } catch (UserNotFoundException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        return;
    }

    @Override
    public void showBalance(String bankName, String userName, int emiNo) {

        try{
            User user = userDao.getUser(userName);
            Optional<Account> foundAccount = getAccountForUser(user,bankName);
            if (!foundAccount.isPresent()){
                throw new AccountNotFoundException("Account not found for the user");
            }
            Account userAccount = foundAccount.get();

            double emiPayments = emiNo * userAccount.getEmiValue();
            double lumpsumPayments = 0;
            Lumpsum dummy = new Lumpsum(emiNo+1,0);
            Lumpsum closestEmi = userAccount.getPaidAmounts().lower(dummy);
            if (closestEmi!=null)lumpsumPayments+=closestEmi.getAmount();

            double totalPaymentDone = lumpsumPayments + emiPayments;
            int emiLeft = (int) Math.ceil(userAccount.getTime()*12 - totalPaymentDone/userAccount.getEmiValue());
            System.out.println("" + bankName + " " + userName + " "+  (int)totalPaymentDone + " " +  emiLeft);

        } catch (UserNotFoundException | AccountNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    protected Optional<Account> getAccountForUser(User user, String bankName){

        return user.getAccountList().stream()
                    .filter(account-> account.getBankName().equals(bankName))
                    .findFirst();
    }
}
