package org.example.proxy;

public class PaymentTransactionProxy implements PaymentService {
    private final PaymentService realPaymentService;

    public PaymentTransactionProxy(PaymentService realPaymentService) {
        this.realPaymentService = realPaymentService;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Transaction started...");
        try {
            realPaymentService.processPayment(amount);
            System.out.println("Transaction committed.");
        } catch (Exception e) {
            System.out.println("Transaction rolled back due to error: " + e.getMessage());
        } finally {
            System.out.println("Transaction finished.");
        }
    }
}
