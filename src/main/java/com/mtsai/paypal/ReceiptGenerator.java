package com.mtsai.paypal;

import com.paypal.core.LoggingManager;
import com.paypal.ipn.IPNMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ReceiptGenerator", urlPatterns = {"receiptGenerator"}, loadOnStartup = 1)
public class ReceiptGenerator extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.getWriter().print("Hello, World!");  
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // For a full list of configuration parameters refer in wiki page.
        // (https://github.com/paypal/sdk-core-java/blob/master/README.md)
        Map<String,String> configurationMap =  Configuration.getConfig();
        IPNMessage 	ipnlistener = new IPNMessage(request,configurationMap);
        boolean isIpnVerified = ipnlistener.validate();
        String transactionType = ipnlistener.getTransactionType();
        Map<String,String> map = ipnlistener.getIpnMap();

        LoggingManager.info(ReceiptGenerator.class, "******* IPN (name:value) pair : "+ map + "  " +
                "######### TransactionType : "+transactionType+"  ======== IPN verified : "+ isIpnVerified);
    }
}
