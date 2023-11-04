package com.bill24.b24paymentsdk.helper.translateLanguage;

import com.bill24.b24paymentsdk.helper.Translate;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;

public class TranslateLanguage {

    public String getScanToPay() {
        return scanToPay;
    }

    public void setScanToPay(String scanToPay) {
        this.scanToPay = scanToPay;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getTranHasExpired() {
        return tranHasExpired;
    }

    public void setTranHasExpired(String tranHasExpired) {
        this.tranHasExpired = tranHasExpired;
    }

    public String getTryAgainToContinue() {
        return tryAgainToContinue;
    }

    public void setTryAgainToContinue(String tryAgainToContinue) {
        this.tryAgainToContinue = tryAgainToContinue;
    }

    public String getTryAgain() {
        return tryAgain;
    }

    public void setTryAgain(String tryAgain) {
        this.tryAgain = tryAgain;
    }
    public String getOr() {
        return or;
    }

    public void setOr(String or) {
        this.or = or;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getInvoicePaidDone() {
        return invoicePaidDone;
    }

    public void setInvoicePaidDone(String invoicePaidDone) {
        this.invoicePaidDone = invoicePaidDone;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getBankRef() {
        return bankRef;
    }

    public void setBankRef(String bankRef) {
        this.bankRef = bankRef;
    }

    public String getToMerchant() {
        return toMerchant;
    }

    public void setToMerchant(String toMerchant) {
        this.toMerchant = toMerchant;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    private String paymentMethod;
    private String totalAmount;
    private String scanToPay;
    private String download;
    private String share;
    private String tranHasExpired;
    private String tryAgainToContinue;
    private String tryAgain;
    private String or;
    private String invoicePaidDone;
    private String transactionNo;
    private String bankRef;
    private  String toMerchant;
    private  String transactionDate;
    private String total;
    private String done;


    public static TranslateLanguage translateLanguage(String language){
        TranslateLanguage translateLanguage=new TranslateLanguage();
        if(language.equals(LanguageCode.EN)){
            translateLanguage.setPaymentMethod(Translate.PAYMENT_METHOD_EN);
            translateLanguage.setTotalAmount(Translate.TOTAL_AMOUNT_EN);
            translateLanguage.setScanToPay(Translate.SCAN_TO_PAY_EN);
            translateLanguage.setDownload(Translate.DOWNLOAD_EN);
            translateLanguage.setShare(Translate.SHARE_EN);
            translateLanguage.setTranHasExpired(Translate.TRAN_EXPIRE_EN);
            translateLanguage.setTryAgainToContinue(Translate.TRY_AGAIN_TITLE_EN);
            translateLanguage.setTryAgain(Translate.TRY_AGAIN_EN);
            translateLanguage.setOr(Translate.OR_EN);
            translateLanguage.setInvoicePaidDone(Translate.INVOICE_PAID_DONE_EN);
            translateLanguage.setTransactionNo(Translate.TRANSACTION_EN);
            translateLanguage.setBankRef(Translate.BANK_REF_EN);
            translateLanguage.setToMerchant(Translate.TO_MERCHANT_EN);
            translateLanguage.setTransactionDate(Translate.TRAN_DATE_EN);
            translateLanguage.setTotal(Translate.TOTAL_EN);
            translateLanguage.setDone(Translate.DONE_EN);
            return  translateLanguage;
        }else {
            translateLanguage.setPaymentMethod(Translate.PAYMENT_METHOD_KM);
            translateLanguage.setTotalAmount(Translate.TOTAL_AMOUNT_KM);
            translateLanguage.setScanToPay(Translate.SCAN_TO_PAY_KM);
            translateLanguage.setDownload(Translate.DONWLOAD_KM);
            translateLanguage.setShare(Translate.SHARE_KM);
            translateLanguage.setTranHasExpired(Translate.TRAN_EXPIRE_KM);
            translateLanguage.setTryAgainToContinue(Translate.TRY_AGAIN_TITLE_KM);
            translateLanguage.setTryAgain(Translate.TRY_AGAIN_KM);
            translateLanguage.setOr(Translate.OR_KM);
            translateLanguage.setInvoicePaidDone(Translate.INVOICE_PAID_DONE_KM);
            translateLanguage.setTransactionNo(Translate.TRANSACTION_KM);
            translateLanguage.setBankRef(Translate.BANK_REF_KM);
            translateLanguage.setToMerchant(Translate.TO_MERCHANT_KM);
            translateLanguage.setTransactionDate(Translate.TRAN_DATE_KM);
            translateLanguage.setTotal(Translate.TOTAL_KM);
            translateLanguage.setDone(Translate.DONE_KM);
            return translateLanguage;
        }

    }
}
