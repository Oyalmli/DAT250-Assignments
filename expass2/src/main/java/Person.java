import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "join_person_address",
            joinColumns = @JoinColumn(name = "person_fk"),
            inverseJoinColumns = @JoinColumn(name = "address_fk"))
    private List<Address> address_list = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<CreditCard> creditcards = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress_list(List<Address> address_list) {
        this.address_list = address_list;
    }

    public String list_addresses(){
        return address_list.toString();
    }

    public void addcard(CreditCard creditCard){
        if(creditcards.contains(creditCard)) return;

        creditcards.add(creditCard);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address_list=" + address_list +
                ", creditcards=" + creditcards +
                '}';
    }
}
