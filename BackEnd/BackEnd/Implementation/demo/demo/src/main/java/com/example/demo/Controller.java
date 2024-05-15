package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Calendar;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.Address;
//import org.bitcoinj.core.SendRequest;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Coin;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

@RestController
public class Controller {

    private DatabaseHandler dh = DatabaseHandler.getInstance();
    private UserHandler uh = UserHandler.getInstance();
    private Customer customer = dh.createCustomer(uh.getLocalID());

    // Returns true if customer password is validated.
    @GetMapping(path = "/user")
    public boolean getGreeting() {
        if (uh.validateCustomerPassword(1, "testing123"))
        {
            return true;
        }
        return false;
    }

    // REMOVE REMOVE REMOVE REMOVE
    @GetMapping(path = "/customerPass/")
    public String[] getPass() {
        return new String[] {"as","sa","zd","ba"};
    }

    // Validates the customer's login attempt using a PIN and creates a new customer object.
    @PostMapping(path = "/customerPin/")
    public ResponseEntity<String> postPin(@RequestBody String pin) {
        System.out.println(pin);
        boolean validation = uh.validatePinLoginAttempt(pin);
        if (validation) {
            customer = dh.createCustomer(uh.getLocalID());
        } 
        return new ResponseEntity<>(Boolean.toString(validation), HttpStatus.OK);
    }
    
    // Creates a new consultancy session object and adds it to the customer's session list.    
    @PostMapping(path = "/createBooking/")
    public ResponseEntity<String> createBooking(@RequestBody String[] sessionDetails) throws ParseException {
        int consultantID = Integer.parseInt(sessionDetails[0]);
        String consultantName = sessionDetails[1];
        int customerID = uh.getLocalID();

        Calendar bookingTimeStamp = Calendar.getInstance();
        Calendar sessionDateTime = Calendar.getInstance();
        bookingTimeStamp.setTime(new Date());
        sessionDateTime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sessionDetails[2]));
        String meetingURL = "https://meet.google.com/nnv-vxch-ddz";


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String bookingTime = dateFormat.format(bookingTimeStamp.getTime());

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sessionTime = dateFormat2.format(sessionDateTime.getTime());

        System.out.println(""+consultantID+", "+consultantName+", "+customerID+", "+bookingTime+", "+sessionTime+", "+meetingURL);

        ConsultancySession cs = new ConsultancySession(consultantID, consultantName, customerID, bookingTimeStamp, sessionDateTime, meetingURL);
        boolean response = dh.createConsultancySession(cs, customer);

        return new ResponseEntity<>(Boolean.toString(response), HttpStatus.OK);
    }

    // Cancels a consultancy session object and removes it from the customer's session list.
    @PostMapping(path = "/cancelBooking/")
    public ResponseEntity<String> cancelBooking(@RequestBody int sessionID) throws ParseException {        
        boolean response = dh.cancelConsultancySession(sessionID, customer);

        return new ResponseEntity<>(Boolean.toString(response), HttpStatus.OK);
    }


    // Returns a list of booked sessions for the customer.
    @GetMapping(path ="/viewBookedSessions")
    public ArrayList<String[]> getBookedSessions() {
        ArrayList<String[]> booked = new ArrayList<>();
        ArrayList<ConsultancySession> sessions = customer.getConsultancySessions();
        for (ConsultancySession session : sessions) {
            String[] sessionDetails = {
                String.valueOf(session.getsessionID()),
                String.valueOf(session.getConsultantID()),
                session.getConsultantName(),
                String.valueOf(session.getCustomerID()),
                session.getBookingTimestamp().getTime().toString(),
                session.getSessionDateTime().getTime().toString(),
                session.getMeetingURL()
            };
            booked.add(sessionDetails);
        }
        return booked;
    }

    // Processes a Bitcoin transaction for the customer and store in DB.
    @PostMapping(path = "/makeTransactionBTC/")
    public ResponseEntity<String> makeBitcoinTransaction(@RequestBody String[] transactionDetails, @Autowired Wallet wallet) throws ParseException {
        int transactionID = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String receiverPublicAddr = transactionDetails[0];
        float amount = Float.parseFloat(transactionDetails[1]);
        ArrayList<Cryptocurrency> cryptocurrencies = wallet.getCryptocurrencies();
        for(Cryptocurrency cryptocurrency : cryptocurrencies){
            if(cryptocurrency.getName().equals("Bitcoin")){
                try {
                    // setup BitcoinJ wallet
                    NetworkParameters params = MainNetParams.get();
                    String privateKeyString = cryptocurrency.getPrivateKey();
                    // Create a DumpedPrivateKey object from a Base58 private key
                    DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(params, privateKeyString);
                    // Convert the DumpedPrivateKey object to an ECKey object
                    ECKey ecKey = dumpedPrivateKey.getKey();
                    //Address senderAddress = Address.fromBase58(params, cryptocurrency.getPublicKey());
                    //wallet.importKey(ecKey);
                    // setup transaction
                    //SendRequest req = SendRequest.to(senderAddress, Coin.valueOf((long) (amount * 1e8)));
                    //req.feePerKb = Coin.ZERO;
                    //Transaction btcTransaction = wallet.sendCoinsOffline(req);
                    //System.out.println("Transaction successfully created: " + btcTransaction.getHashAsString());
                } catch (Exception e) {
                    return new ResponseEntity<>("Error creating Bitcoin transaction: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity<>(Boolean.toString(true), HttpStatus.OK);
    }


    // Processes an Ethereum transaction for the customer and store in DB
    @PostMapping(path = "/makeTransactionETH/")
    public ResponseEntity<String> makeEthereumTransaction(@RequestBody String[] transactionDetails) throws ParseException {
        int transactionID = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String senderPublicAddr = transactionDetails[0];
        String receiverPublicAddr = transactionDetails[1];
        float amount = Float.parseFloat(transactionDetails[3]);
    
        try {
            // setup Web3J credentials
            //Credentials credentials = Credentials.create(cryptocurrency.getPrivateKey());
            // setup Web3J transaction manager
            Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"));
            //TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
            // setup Ethereum transaction
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(senderPublicAddr, DefaultBlockParameterName.LATEST).send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            BigInteger gasPrice = BigInteger.valueOf(20000000000L);
            BigInteger gasLimit = BigInteger.valueOf(21000);
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, receiverPublicAddr, Convert.toWei(BigDecimal.valueOf(amount), Convert.Unit.ETHER).toBigInteger());
            // sign and send transaction
            //byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            //String hexValue = Numeric.toHexString(signedMessage);
            //EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedMessage.toString()).send();
    
            //if (ethSendTransaction.getTransactionHash() != null) {
            //    String txHash = ethSendTransaction.getTransactionHash();
            //    return new ResponseEntity<>(txHash, HttpStatus.OK);
            //} else {
                return new ResponseEntity<>("Transaction failed", HttpStatus.INTERNAL_SERVER_ERROR);
            //}
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while making transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(path ="/transactionHistory")
    public ArrayList<String[]> getTransactionHistory() throws Exception {
        ArrayList<Transaction> transactions = dh.getTransactionHistory(customer.getCustomerID());
        ArrayList<String[]> transactionDetails = new ArrayList<>();
        for (Transaction transaction : transactions) {
            String[] details = {
                String.valueOf(transaction.getID()),
                transaction.getDate().toString(),
                transaction.getCrypto(),
                String.valueOf(transaction.getSuspicious()),
                transaction.getReceiverPA(),
                transaction.getSenderPA(),
                String.valueOf(transaction.getAmount())
            };
            transactionDetails.add(details);
        }
        return transactionDetails;
    }

    // Retrieves available sessions for the current day and returns them in an ArrayList of String arrays.
    @GetMapping(path ="/viewAvailableSessions")
    public ArrayList<String[]> viewAvailableSessions() {
        ArrayList<String[]> available = new ArrayList<>();

        ArrayList<String[]> consultantsAvailable = dh.getAvailableConsultants();
        int consultantID = Integer.parseInt(consultantsAvailable.get(0)[0]);
        String consultantName = consultantsAvailable.get(0)[1];
        ArrayList<Calendar> sessionDates;
        try {
            sessionDates = dh.getAvailableSessionsToday(consultantID);
            for (Calendar sessionDate : sessionDates)
            {
                String[] sessionDetails = {
                    String.valueOf(consultantID),
                    consultantName,
                    sessionDate.getTime().toString()
                };
                available.add(sessionDetails);
            }
            return available;
        } catch (ParseException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // REMOVE REMOVE REMOVE REMOVE
    @GetMapping(path="/customer")
    public String[] getCustomer(){
        Customer customer = dh.createCustomer(uh.getLocalID());
        return new String[] {customer.getPin(),"sa","zd","ba"};
    }
    
    // REMOVE REMOVE REMOVE REMOVE REMOVE
    @GetMapping(path="/wallet")
    public String[] getWallet(){
        return new String[] {"as","sa","zd","ba"};
    }
    
    // Retrieves the public and private keys for all customers 
    // from the database and returns them in a HashMap.
    @GetMapping(path="/keys")
    public HashMap<String,String> getKeys()
    {
        DatabaseHandler dh = DatabaseHandler.getInstance();
        return dh.getCustomerKeys();
    }

    // Retrieves and returns various details about the customer's wallet, 
    // including total balance and details for each cryptocurrency.
    @GetMapping(path = "/walletDetails")
    public HashMap<String,String[]> getWalletDetails()
    {

        float totalBalance = customer.getWallet().getBalance();
        HashMap<String,String[]> walletDetails = new HashMap<>();
        String[] totalBalanceInArray = {String.valueOf(totalBalance)};
        walletDetails.put("Total balance", totalBalanceInArray);

        ArrayList<Cryptocurrency> cryptos = customer.getWallet().getCryptocurrencies();
        for (Cryptocurrency crypto : cryptos)
        {
            ArrayList<String> details = new ArrayList<>();
            details.add(String.valueOf(crypto.getPrice()));
            details.add(String.valueOf(crypto.getBalance()));
            details.add(crypto.getPublicKey());
            details.add(crypto.getPrivateKey());
            String[] detailsArray = details.toArray(new String[details.size()]);
            walletDetails.put(crypto.getName(),detailsArray);
        }

        System.out.println(walletDetails);
        System.out.println(Arrays.toString(walletDetails.get("Bitcoin")));
        System.out.println(Arrays.toString(walletDetails.get("Ethereum")));

        return walletDetails;
    }
}