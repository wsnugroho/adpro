package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.model.Order;
import id.ac.ui.id.cs.adprog.eshop.model.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    public Payment addPayment(Order order, String method, Map<String, String> paymentData);

    public Payment setStatus(Payment payment, String status);

    public Payment getPayment(String paymentId);

    public List<Payment> getAllPayments();
}
