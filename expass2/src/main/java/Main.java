import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "TEST";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console


        em.getTransaction().begin();

        Person person = new Person();
        person.setName("Test Larsen" + (int)(Math.random()*1000));

        Address address = new Address();
        address.setAddress("Adresse", (int)(Math.random()*1000));
        List<Address> adr_list = new ArrayList<>();
        adr_list.add(address);
        person.setAddress_list(adr_list);

        Pincode pincode = new Pincode();
        pincode.setCount((int)(Math.random()*10));
        pincode.setPincode(String.valueOf((int)(Math.random()*10000)));
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance((int)(Math.random()*10000));
        creditCard.setLimit((int)(Math.random()*1000));
        creditCard.setPincode(pincode);
        creditCard.setNumber((int)(Math.random()*100000000));
        Bank bank = new Bank();
        creditCard.setBank(bank);
        bank.setName("BANK" + (int)(Math.random()*100));
        bank.addcard(creditCard);
        person.addcard(creditCard);

        em.persist(address);
        em.persist(person);
        em.persist(pincode);
        em.persist(bank);
        em.persist(creditCard);
        em.getTransaction().commit();

        Query q = em.createQuery("select p from Person p");
        List<Person> personList = q.getResultList();
        for (Person per : personList) {
            System.out.println(per);
        }
        System.out.println("Size: " + personList.size());

        em.close();
    }
}

