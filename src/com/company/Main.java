package com.company;
import express.Express;
import express.middleware.Middleware;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //creates app variable
        Express app = new Express();
        Database db = new Database();

        app.get("/rest/items", (req,res)->{
            List<Items> items = db.getItems();
            res.json(items);
        });

        app.post("/rest/items",(req,res)->{
            Items title = (Items) req.getBody(Items.class);
            System.out.println(title.toString());
            db.createItem(title);
            res.send("Post OK");
        });

        app.delete("/rest/items",(req,res)->{

        });

        try {
            app.use(Middleware.statics(Paths.get("src/www").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        app.listen(3000); // defaults to port 80
        System.out.println("Server started on port 3000");
    }
}
