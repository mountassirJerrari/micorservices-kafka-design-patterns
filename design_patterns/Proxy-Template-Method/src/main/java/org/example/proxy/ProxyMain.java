package org.example.proxy;

public class ProxyMain {
    public static void main(String[] args) {
        System.out.println("--- Proxy Pattern Demonstration ---");
        PaymentService realService = new RealPaymentService();
        PaymentService proxyService = new PaymentTransactionProxy(realService);

        proxyService.processPayment(100.0);
    }
}
