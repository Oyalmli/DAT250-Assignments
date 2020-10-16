import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static spark.Spark.*;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "TODO";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {

        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        TodoController todoController = new TodoController(em);

        after((req, res) -> {
            res.type("application/json");
        });

        //CREATE
        post("/todos", todoController::createTodo);

        //READ
        get("/todos", todoController::getTodos);

        //UPDATE
        put("/todos", todoController::updateTodo);

        //DELETE
        delete("/todos", todoController::deleteTodo);


    }
}
