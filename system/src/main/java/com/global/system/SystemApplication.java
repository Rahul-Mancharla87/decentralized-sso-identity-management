package com.global.system;

import java.io.IOException;
import java.math.BigInteger;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import com.global.system.service.FileUploadService;

@SpringBootApplication
public class SystemApplication implements CommandLineRunner {

	@Autowired
	FileUploadService storageService;

  /*  private final Web3j web3j;

    public SystemApplication(Web3j web3j) {
        this.web3j = web3j;
    } */



	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}
	

	@Override
  public void run(String... arg) throws Exception {
//    storageService.deleteAll();
    storageService.init();
  }

   /* @jakarta.annotation.PostConstruct
    public void listen() {

        web3j.transactionFlowable().subscribe(tx -> {

          //  LOGGER.info("New tx: id={}, block={}, from={}, to={}, value={}", tx.getHash(), tx.getBlockHash(), tx.getFrom(), tx.getTo(), tx.getValue().intValue());

            try {

                EthCoinbase coinbase = web3j.ethCoinbase().send();
                EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(tx.getFrom(), DefaultBlockParameterName.LATEST).send();
             //   LOGGER.info("Tx count: {}", transactionCount.getTransactionCount().intValue());

                if (transactionCount.getTransactionCount().intValue() % 10 == 0) {

                    EthGetTransactionCount tc = web3j.ethGetTransactionCount(coinbase.getAddress(), DefaultBlockParameterName.LATEST).send();
                    Transaction transaction = Transaction.createEtherTransaction(coinbase.getAddress(), tc.getTransactionCount(), tx.getValue(), BigInteger.valueOf(21_000), tx.getFrom(), tx.getValue());
                    web3j.ethSendTransaction(transaction).send();

                }

            } catch (IOException e) {
               // LOGGER.error("Error getting transactions", e);
            }

        });

      //  LOGGER.info("Subscribed");

    } */

}


