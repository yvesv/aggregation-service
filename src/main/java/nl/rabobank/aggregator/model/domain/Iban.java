package nl.rabobank.aggregator.model.domain;

public class Iban {

    private String landCode;
    private String checkCode;
    private String bankCode;
    private String accountNumber;

    /**
     * I would expect a more professional IBAN library
     * to handle this logic with more security.
     *
     * @param iban
     */
    private Iban(final String iban) {
        setLandCode(iban.substring(0, 2));
        setLandCode(iban.substring(2, 4));
        setBankCode(iban.substring(4, 8));
        setAccountNumber(iban.substring(8));
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static Iban valueOf(final String iban) {
        if (iban == null || 17 != iban.length()) {
            throw new IllegalArgumentException("Invalid length");
        }
        return new Iban(iban);
    }

    @Override
    public String toString() {
        return "Iban{" +
                "landCode='" + landCode + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
