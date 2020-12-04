package mongo;

//import com.mongodb.MongoClient;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import netscape.javascript.JSObject;
import org.bson.Document;
import personicle.query.JSONQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.eq;

public class BigFoodTest {
    private static void filterQuery() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://172.16.2.225:27017")) {

            MongoDatabase database = mongoClient.getDatabase("PersonicleServer");

            MongoCollection<Document> collection = database.getCollection("FoodLog");

            /*FindIterable fit = collection.find(and(lt("id", 50000),
                    gt("price", 20000))).sort(new Document("price", -1));*/
            long begin = System.currentTimeMillis();
            // FindIterable fit = collection.find(and(eq("userName", "范曦燎"), lt("timestamp", 492950204)));//.sort(new Document("foodname", -1));
            FindIterable fit = collection.find(eq("userName", "墨问亮"));
            List<Document> docs = new ArrayList<Document>();

            fit.into(docs);
            System.out.println(System.currentTimeMillis() - begin);
            begin = System.currentTimeMillis();

            // System.out.println(collection.find(eq("userName", "养从亦")));
            /*for (Document doc : docs) {
                System.out.println(doc);
            }*/
            System.out.println(System.currentTimeMillis() - begin + ", " + docs.size());
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

    /*private static void sizeQuery() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://172.16.2.225:27017")) {

            MongoDatabase database = mongoClient.getDatabase("PersonicleServer");
            // DBObject query = new BasicDBObject("otherInfo.text", new BasicDBObject("$exists", true));
            FindIterable result = database.getCollection("FoodLog").count(database.getCollection("FoodLog").find(eq("userName", "范曦燎")));
            System.out.println(result.size());
        }
    }*/

    private static void rrQuery() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://172.16.2.225:27017")) {

            MongoDatabase database = mongoClient.getDatabase("PersonicleServer");

            MongoCollection<Document> collection = database.getCollection("FoodLog");
            List<JSONObject> queries = JSONQuery.getQuery();
            long begin = System.currentTimeMillis();
            int count = 0;
            for (JSONObject query : queries) {
                FindIterable fit = collection.find(eq("userName", query.get("userName")));
                List<Document> docs = new ArrayList<Document>();
                fit.into(docs);
                count += docs.size();
                System.out.println(docs.size());
            }
            System.out.println(count + " " + (System.currentTimeMillis() - begin));
        }
    }

    public static void main(String[] args) {
        // listQuery();
        // filterQuery();
        rrQuery();
    }
}
