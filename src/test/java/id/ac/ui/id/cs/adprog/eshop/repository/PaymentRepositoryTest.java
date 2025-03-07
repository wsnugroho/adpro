package id.ac.ui.id.cs.adprog.eshop.repository;

import id.ac.ui.id.cs.adprog.eshop.enums.PaymentMethod;
import id.ac.ui.id.cs.adprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Map<String, String> paymentVoucher = new HashMap<>();
        paymentVoucher.put("voucherCode", "ESHOP12345678ABC");
        Payment payment1 = new Payment("8c806293-efd5-434a-90cd-f4994197507b", PaymentMethod.VOUCHER_CODE.getValue(), paymentVoucher);
        payments.add(payment1);
        Map<String, String> paymentBank = new HashMap<>();
        paymentBank.put("bankName", "Bank Adpro");
        paymentBank.put("referenceCode", "DEADBEEF");
        Payment payment2 = new Payment("8c806293-efd5-434a-90cd-f4994197507c", PaymentMethod.BANK_TRANSFER.getValue(), paymentBank);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getPaymentData());
        Payment result = paymentRepository.save(newPayment);

        Payment paymentResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), paymentResult.getId());
        assertEquals(payment.getMethod(), paymentResult.getMethod());
        assertNull(paymentResult.getStatus());
        assertEquals(payment.getPaymentData(), paymentResult.getPaymentData());
    }

    @Test
    void testFindByIdIfFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findPayment = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findPayment.getId());
        assertEquals(payments.get(1).getMethod(), findPayment.getMethod());
        assertEquals(payments.get(1).getStatus(), findPayment.getStatus());
        assertSame(payments.get(1).getPaymentData(), findPayment.getPaymentData());
    }

    @Test
    void testFindByIdIfNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findPayment = paymentRepository.findById("hello world");
        assertNull(findPayment);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> findPayments = paymentRepository.findAll();
        assertEquals(payments.size(), findPayments.size());
    }
}
