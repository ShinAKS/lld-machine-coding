package com.ayush.mymoney.commands.executors;

import com.ayush.mymoney.commands.CommandExecutor;
import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.service.PortfolioService;
import com.ayush.mymoney.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SipCommandExecutor extends CommandExecutor {

    public static final String SIP = "SIP";

    public SipCommandExecutor(PortfolioService portfolioService) {
        super(portfolioService);
    }


    @Override
    public Boolean isValid(Command command) {
        return command.getParams().size()== Constants.MAX_FUNDS +1;
    }

    @Override
    protected void executeValidCommand(Command command) {
        List<String> params = command.getParams();
        List<Double> sipValues = new ArrayList<>();
        int numArgs = params.size();

        for (int i = 1 ; i<numArgs ; i++){
            sipValues.add(Double.valueOf(params.get(i)));
        }
        this.portfolioService.initiateSip(sipValues);
    }
}
