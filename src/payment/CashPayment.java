package payment;

public class CashPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        return amount > 0;
    }

    @Override
    public String getMethodName() {

        return "CASH";
    }
}