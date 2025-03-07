package id.ac.ui.id.cs.adprog.eshop.repository;

import id.ac.ui.id.cs.adprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private final List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
    }

    public Payment findById(String paymentId) {
    }

    public List<Payment> findAll() {
    }
}
