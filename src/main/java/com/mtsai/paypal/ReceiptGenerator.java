package com.mtsai.paypal;

import com.mtsai.paypal.dao.Table;
import com.paypal.core.LoggingManager;
import com.paypal.ipn.IPNMessage;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@WebServlet(name = "ReceiptGenerator", urlPatterns = {"receiptGenerator"}, loadOnStartup = 1)
public class ReceiptGenerator extends HttpServlet {
    public void init() throws ServletException {
        Table.createAll();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print("Hello, World!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> configurationMap = Configuration.getConfig();
        configurationMap.put("service.IPNEndpoint", "https://ipnpb.sandbox.paypal.com/cgi-bin/webscr");
        IPNMessage ipnlistener = new IPNMessage(request, configurationMap);
        boolean isIpnVerified = ipnlistener.validate();
        String transactionType = ipnlistener.getTransactionType();
        Map<String, String> map = ipnlistener.getIpnMap();

        LoggingManager.info(ReceiptGenerator.class, "******* IPN (name:value) pair : " + map + "  " +
                "######### TransactionType : " + transactionType + "  ======== IPN verified : " + isIpnVerified);
    }
}
