package id.ac.ui.id.cs.adprog.eshop.model;

import id.ac.ui.id.cs.adprog.eshop.enums.PaymentMethod;
import id.ac.ui.id.cs.adprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;
        this.setMethod(method);
        this.validateData();
    }

    public void setStatus(String status) {
        if (this.status == null && PaymentStatus.contains(status)) {
            this.status = status;
        } else if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException();
        }
    }

    private void setMethod(String method) {
        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void validateData() {
        switch (PaymentMethod.valueOf(method)) {
            case PaymentMethod.VOUCHER_CODE:
                if (validateVoucherMethod()) {
                    this.status = PaymentStatus.SUCCESS.getValue();
                } else {
                    this.status = PaymentStatus.REJECTED.getValue();
                }
                break;
            case PaymentMethod.BANK_TRANSFER:
                if (!validateBankMethod()) {
                    this.status = PaymentStatus.REJECTED.getValue();
                }
                break;
            default:
                break;
        }
    }

    private boolean validateVoucherMethod() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return false;
        }
        return voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                voucherCode.substring(5).chars().filter(Character::isDigit).count() == 8;
    }

    private boolean validateBankMethod() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");
        boolean isBankNameValid = bankName != null && !bankName.isEmpty();
        boolean isReferenceCodeValid = referenceCode != null && !referenceCode.isEmpty();
        return isBankNameValid && isReferenceCodeValid;
    }
}
