package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.enums.PaymentMethod;
import id.ac.ui.id.cs.adprog.eshop.model.Order;
import id.ac.ui.id.cs.adprog.eshop.model.Payment;
import id.ac.ui.id.cs.adprog.eshop.model.Product;
import id.ac.ui.id.cs.adprog.eshop.repository.OrderRepository;
import id.ac.ui.id.cs.adprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    Order order;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1709560000L, "Safira Sudrajat");

        Map<String, String> validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP80235gh021f");
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "ZCZCW08255L18A");
        Map<String, String> validBankData = new HashMap<>();
        validBankData.put("bankName", "MeongMeongMeong");
        validBankData.put("referenceCode", "ea5f7b695ec97579a6f5e767ea5f9");

        Payment payment1 = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), validVoucherData);
        Payment payment2 = new Payment("7f915bb-4b15-42f4-aebc-c3af385fb078", PaymentMethod.VOUCHER_CODE.getValue(), invalidVoucherData);
        Payment payment3 = new Payment("e334ef40-9eff-4da8-9487-8ee697ecbf1e", PaymentMethod.BANK_TRANSFER.getValue(), validBankData);
        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testAddPaymentIfAlreadySucceed() {
        Payment payment = payments.getFirst();
        assertNull(paymentService.addPayment(this.order, payment.getMethod(), payment.getPaymentData()));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testUpdateInvalidStatus() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "awokaowk");
        });
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdateNonexistentPayment() {
        Payment payment = new Payment("woh", PaymentMethod.VOUCHER_CODE.getValue(), new HashMap<>());
        doReturn(null).when(paymentRepository).findById("woh");

        assertThrows(NoSuchElementException.class, () -> {
            paymentService.setStatus(payment, "woh");
        });

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentById() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testGetPaymentByNonexistentId() {
        doReturn(null).when(paymentRepository).findById("kelazzz");
        assertNull(paymentService.getPayment("kelazzz"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments.size(), results.size());
    }
}
