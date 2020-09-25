import javax.persistence.*;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private int limit;
    private int balance;

    @OneToOne
    private Pincode pincode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bank bank;

    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Pincode getPincode() {
        return pincode;
    }
    public void setPincode(Pincode pincode) {
        this.pincode = pincode;
    }

    public Bank getBank() {
        return bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number=" + number +
                ", limit=" + limit +
                ", balance=" + balance +
                ", pincode=" + pincode +
                ", bank=" + bank +
                '}';
    }
}
