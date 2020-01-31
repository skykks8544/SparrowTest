import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Writer: Date: 9/17/12
 */
public class MISSING_AUTHENTICATION_FOR_CRITICAL_FUNCTION_TestCase {
    class BankAccount {
        private double balance;
        private String accountNumber;
        private String person;

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public void setToPerson(String person) {
            this.person = person;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    static class AccountManager {
        public static void send(BankAccount account) {
        }
    }

    public void sendAccount(String accountNumber, double balance) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setToPerson("toPerson");
        account.setBalance(balance);
        AccountManager.send(account); /* BUG */
    }

    public void safeSendAccount(HttpServletRequest request, HttpSession session, String accountNumber, double balance) {
        String newUserName = request.getParameter("username");
        String newPassword = request.getParameter("password");
        String password = session.getValue("password").toString();
        String userName = session.getValue("username").toString();

        // 재인증을 통해서 이체여부를 판단한다.
        // if 문 안에 있으면 안전하다고 판단한다.
        if (newUserName.equals(userName) && newPassword.equals(password)) {
            BankAccount account = new BankAccount();
            account.setAccountNumber(accountNumber);
            account.setToPerson("toPerson");
            account.setBalance(balance);
            AccountManager.send(account); /* SAFE */
        }
    }
}
