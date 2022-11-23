package books;
import java.util.*;
import jrails.JRouter;
import jrails.JServer;

public class Main {
    public static void main(String[] args) {
        JRouter r = new JRouter();

        r.addRoute("GET", "/", BookController.class, "index");
        r.addRoute("GET", "/show", BookController.class, "show");
        r.addRoute("GET", "/new", BookController.class, "new_book");
        r.addRoute("GET", "/edit", BookController.class, "edit");
        r.addRoute("POST", "/create", BookController.class, "create");
        r.addRoute("POST", "/update", BookController.class, "update");
        r.addRoute("GET", "/destroy", BookController.class, "destroy"); // Should be DELETE but no way to do that with a link

        
        JServer.start(r);


        r.getRoute("GET", "/edit");

        HashMap<String, String> params = new HashMap<>();
        r.route("GET", "/edit", params);
        
        Book b = new Book();
        b.title = "Hunger Games";
        b.author = "Chimmy Chunga";
        b.num_copies = 30;
        b.save();

        Book c = new Book();
        c.title = "Hungry Games";
        c.author = "Slimmy Chunga";
        c.num_copies = 10;
        c.save();

        Book.reset();

        Book d = new Book();
        d.title = "Dumb Bitches";
        d.author = "The real Slim Shady";
        d.num_copies = 1000;
        d.save();
    }
}
