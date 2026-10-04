package payment;

public class Cardpayment implements PaymentMethod {

    private String cardNumber;

    public Cardpayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {

        String card =
                cardNumber.replaceAll("\\s+", "");

        return card.length() >= 12;
    }

    @Override
    public String getMethodName() {
        return "Card";
    }
}