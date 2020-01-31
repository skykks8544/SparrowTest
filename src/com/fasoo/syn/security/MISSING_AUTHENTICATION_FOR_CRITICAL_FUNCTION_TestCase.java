package com.fasoo.syn.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.lang.String;

public class MISSING_AUTHENTICATION_FOR_CRITICAL_FUNCTION_TestCase {
    class BankAccount {
        private double balance;
        private int accountNumber;
        private String person;

        public void setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
        }

        public void setToPerson(String person) {
            this.person = person;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void send() {
        }
    }

    static class AccountManager {
        public static void send(BankAccount account) {
        }

        public int sendAccount(BankAccount account) {
            return 1;
        }
    }

    public void sendBankAccount(int accountNumber, double balance) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setToPerson("toPerson");
        account.setBalance(balance);
        AccountManager.send(account); /* BUG */
    }

    public void safeSendBankAccount(HttpServletRequest request, HttpSession session, int accountNumber, double balance) {
        String newUserName = request.getParameter("username");
        String newPassword = request.getParameter("password");
        String password = session.getValue("password").toString();
        String userName = session.getValue("username").toString();

        // 재인증을 통해서 이체여부를 판단한다.
        // if문 안에 있으면 안전하다고 판단한다.
        if (newUserName.equals(userName) && newPassword.equals(password)) {
            BankAccount account = new BankAccount();
            account.setAccountNumber(accountNumber);
            account.setToPerson("toPerson");
            account.setBalance(balance);
            AccountManager.send(account); /* SAFE */
        }
    }

    public int test(HttpSession session, String newUserName) {
        BankAccount account = new BankAccount();
        // String userName = session.getValue("username");
        String userName = session.getValue("username").toString();

        AccountManager manager = new AccountManager();
        manager.sendAccount(account); /* BUG */
        // if authenticaion test
        if (newUserName.equals(userName)) {
            manager.sendAccount(account); /* SAFE */
        } else if (1 == 1) {
            manager.sendAccount(account); /* BUG */
        }
        // switch authenticaion test
        switch (1) {
        case 1:
            manager.sendAccount(account); /* BUG */
        default:
            break;
        }
        // while authenticaion test
        while (newUserName.equals(userName)) {
            manager.sendAccount(account); /* SAFE */
        }
        // while authenticaion test
        while (1 == 1) {
            manager.sendAccount(account); /* BUG */
        }
    }

    public int test2(HttpSession session, String newUserName) {
        BankAccount account = new BankAccount();
        // String userName = session.getValue("username");
        String userName = session.getValue("username").toString();

        AccountManager manager = new AccountManager();
        manager.sendAccount(account); /* BUG */

        // for authenticaion test
        for (; newUserName.equals(userName);) {
            manager.sendAccount(account); /* SAFE */
        }
        for (;;) {
            manager.sendAccount(account); /* BUG */
        }

    }

    public int test3(HttpSession session, String newUserName) {
        BankAccount account = new BankAccount();
        // String userName = session.getValue("username");
        String userName = session.getValue("username").toString();

        AccountManager manager = new AccountManager();
        manager.sendAccount(account); /* BUG */

        // ternary operator authenticaion test
        if (true/* In some cond */) {
            return 1 == 1 ? manager.sendAccount(account) : 0; /* BUG */
        } else {
            return newUserName.equals(userName) ? manager.sendAccount(account) : 0; /* SAFE */
        }

    }

    public int test4(HttpSession session, String newUserName) {
        BankAccount account = new BankAccount();
        // String userName = session.getValue("username");
        String userName = session.getValue("username").toString();

        AccountManager manager = new AccountManager();
        manager.sendAccount(account); /* BUG */

        for (;;) {
            /* DO NOTHING: NOT A BUG */
        }
    }

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String newUserName;
        String newPassword;
        String password;
        String userName;
        String toPerson;
        int balance, accountNumber;

        newUserName = request.getParameter("username");
        newPassword = request.getParameter("password");
        toPerson = "person";
        balance = 1;
        accountNumber = 11;

        if (newUserName == null || newPassword == null) {
            response.getWriter().println("first data error");
        }

        HttpSession session = request.getSession(true);

        password = session.getAttribute("password").toString();
        userName = session.getAttribute("username").toString();

        if (password == null || userName == null) {
            response.getWriter().println("second data error");
        }

        /* POTENTIAL FLAW: Missing_Authentication_for_Critical_Function */
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setToPerson(toPerson);
        account.setBalance(balance);
        account.send();
    }
}
