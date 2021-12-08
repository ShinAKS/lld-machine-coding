package com.ayush.mymoney;

import com.ayush.mymoney.commands.CommandExecutorFactory;
import com.ayush.mymoney.exceptions.InvalidModeException;
import com.ayush.mymoney.mode.FileMode;
import com.ayush.mymoney.mode.InteractiveMode;
import com.ayush.mymoney.models.Portfolio;
import com.ayush.mymoney.repository.MonthlyPortfolioRepository;
import com.ayush.mymoney.service.PortfolioService;
import com.ayush.mymoney.strategy.AllocationStrategy;
import com.ayush.mymoney.strategy.NormalizedAllocationStrategy;

import java.io.IOException;

public class PortfolioApp {
    public static void main(final String[] args) throws IOException{
        AllocationStrategy allocationStrategy = new NormalizedAllocationStrategy();

        final PortfolioService portfolioService = new PortfolioService(new Portfolio(),allocationStrategy);

        final CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(portfolioService);

        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory).process();
        } else if (isFileInputMode(args)) {
            new FileMode(commandExecutorFactory, args[0]).process();
        } else {
            throw new InvalidModeException();
        }

    }


    private static boolean isFileInputMode(final String[] args) {
        return args.length == 1;
    }

    private static boolean isInteractiveMode(final String[] args) {
        return args.length == 0;
    }

}


