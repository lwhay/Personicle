package mongo;

//import com.mongodb.MongoClient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

public class BigFoodTest {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://172.16.2.225:27017")) {

            MongoDatabase database = mongoClient.getDatabase("Personicle");

            MongoCollection<Document> collection = database.getCollection("cars");

            FindIterable fit = collection.find(and(lt("price", 50000),
                    gt("price", 20000))).sort(new Document("price", -1));

            List<Document> docs = new ArrayList<Document>();

            fit.into(docs);

            for (Document doc : docs) {

                System.out.println(doc);
            }
        }
    }
}
