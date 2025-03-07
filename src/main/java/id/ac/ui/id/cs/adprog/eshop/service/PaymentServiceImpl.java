package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.enums.OrderStatus;
import id.ac.ui.id.cs.adprog.eshop.enums.PaymentStatus;
import id.ac.ui.id.cs.adprog.eshop.model.Order;
import id.ac.ui.id.cs.adprog.eshop.model.Payment;
import id.ac.ui.id.cs.adprog.eshop.repository.OrderRepository;
import id.ac.ui.id.cs.adprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment currPayment = paymentRepository.findById(payment.getId());
        if (currPayment == null) {
            throw new NoSuchElementException();
        }
        payment.setStatus(status);

        Order currOrder = orderRepository.findById(currPayment.getId());
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            currOrder.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
            currOrder.setStatus(OrderStatus.FAILED.getValue());
        }

        return currPayment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
