import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;

public class TodoController {
    EntityManager em;

    public TodoController(EntityManager entityManager){
        this.em = entityManager;
    }

    public Serializable createTodo(Request req, Response res) {
        Gson gson = new Gson();
        em.getTransaction().begin();
        Todo todo = gson.fromJson(req.body(), Todo.class);
        em.persist(todo);
        em.getTransaction().commit();
        return todo;
    }

    public Serializable getTodos(Request req, Response res){
        Query q = em.createQuery("select t from Todo t");
        return (Serializable) q.getResultList();
    }

    public Serializable deleteTodo(Request req, Response res){
        Gson gson = new Gson();
        Todo todo;
        try {
            JsonObject jsonObject = gson.fromJson(req.body(), JsonObject.class);
            JsonElement deleteIdJSON = jsonObject.get("deleteId");
            Long deleteId = Long.parseLong(deleteIdJSON.getAsString());
            todo = em.find(Todo.class, deleteId);
            if (todo == null) throw new Exception("Todo does not exist");
        } catch (Exception e) {
            return e;
        }

        em.getTransaction().begin();
        em.remove(todo);
        em.getTransaction().commit();

        return "deleted " + todo;
    }

    public Serializable updateTodo(Request req, Response res){
        System.out.println("UPDATING# " + req.body());
        Gson gson = new Gson();
        Todo todo;
        JsonObject jsonObject = gson.fromJson(req.body(), JsonObject.class);
        JsonElement updateIdJSON = jsonObject.get("updateId");
        Long updateId = Long.parseLong(updateIdJSON.getAsString());
        JsonElement updateBodyJSON = jsonObject.get("updateBody");
        System.out.println(updateBodyJSON.toString());
        Todo newTodo = gson.fromJson(updateBodyJSON, Todo.class);
        try {
            todo = em.find(Todo.class, updateId);
            if (todo == null) throw new Exception("Todo does not exist");
        } catch (Exception e) {
            return e;
        }

        if(newTodo.getDescription() != null) todo.setDescription(newTodo.getDescription());
        if(newTodo.getSummary() != null) todo.setSummary(newTodo.getSummary());

        em.getTransaction().begin();
        em.persist(todo);
        em.getTransaction().commit();

        return todo;
    }
}
