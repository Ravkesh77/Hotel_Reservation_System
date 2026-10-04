package payment;

public class UPIPayment implements PaymentMethod {

    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {

        return upiId != null &&
               !upiId.trim().isEmpty();
    }

    @Override
    public String getMethodName() {
        return "UPI";
    }
}