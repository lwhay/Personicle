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
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

public class BigFoodTest {
    private static void filterQuery() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://172.16.2.225:27017")) {

            MongoDatabase database = mongoClient.getDatabase("PersonicleServer");

            MongoCollection<Document> collection = database.getCollection("foodlog");

            FindIterable fit = collection.find(and(lt("id", 50000),
                    gt("price", 20000))).sort(new Document("price", -1));

            List<Document> docs = new ArrayList<Document>();

            fit.into(docs);

            for (Document doc : docs) {

                System.out.println(doc);
            }
        }
    }

    private static void listQuery() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        try (MongoClient mongoClient = MongoClients.create("mongodb://172.16.2.225:27017")) {

            MongoDatabase database = mongoClient.getDatabase("PersonicleServer");

            for (String name : database.listCollectionNames()) {
                System.out.println(name);
            }
        }
    }

    public static void main(String[] args) {
        listQuery();
    }
}
