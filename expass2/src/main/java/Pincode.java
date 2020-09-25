import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pincode;
    private int count;

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return pincode;
    }
}
