import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private int number;

    @ManyToMany(mappedBy = "address_list")
    private List<Person> people_living_at_address = new ArrayList<>();

    public void setAddress(String street, int number) {
        this.street = street;
        this.number = number;
    }


    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
