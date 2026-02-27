package com.qea.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Utility class for MongoDB operations.
 * Fetches data as List of Maps for easy comparison with frontend data.
 */
public class MongoDBUtil {

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private boolean authEnabled;
    private boolean tlsEnabled;
    private MongoClient mongoClient;
    private MongoDatabase db;

    public MongoDBUtil() {
        Properties prop = new configReader().init_pop();
        this.host = prop.getProperty("mongo.host", "localhost");
        this.port = prop.getProperty("mongo.port", "27017");
        this.database = prop.getProperty("mongo.database");
        this.username = prop.getProperty("mongo.username", "");
        this.password = prop.getProperty("mongo.password", "");
        this.authEnabled = Boolean.parseBoolean(prop.getProperty("mongo.authEnabled", "false"));
        this.tlsEnabled = Boolean.parseBoolean(prop.getProperty("mongo.tlsEnabled", "false"));
    }

    public MongoDBUtil(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.authEnabled = (username != null && !username.isEmpty());
        this.tlsEnabled = false;
    }

    /**
     * Opens a connection to the MongoDB database.
     */
    public void connect() {
        try {
            StringBuilder uriBuilder = new StringBuilder("mongodb://");

            if (authEnabled && username != null && !username.isEmpty()) {
                uriBuilder.append(username).append(":").append(password).append("@");
            }

            uriBuilder.append(host).append(":").append(port).append("/").append(database);

            // Build query parameters
            List<String> params = new ArrayList<>();
            if (authEnabled) {
                params.add("authSource=admin");
            }
            // NOTE: Do NOT add tls=true to URI when using MongoClientSettings for TLS,
            // as the URI's TLS config would override the custom SSLContext settings.

            if (!params.isEmpty()) {
                uriBuilder.append("?");
                for (int i = 0; i < params.size(); i++) {
                    if (i > 0) uriBuilder.append("&");
                    uriBuilder.append(params.get(i));
                }
            }

            String connectionUri = uriBuilder.toString();
            System.out.println("[MongoDBUtil] Connecting with URI: " +
                    connectionUri.replaceAll(":[^@/]+@", ":****@"));

            if (tlsEnabled) {
                // Create a trust-all SSL context to bypass certificate and hostname verification
                TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                    }
                };
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionUri))
                        .applyToSslSettings(builder -> {
                            builder.enabled(true);
                            builder.invalidHostNameAllowed(true);
                            builder.context(sslContext);
                        })
                        .build();
                mongoClient = MongoClients.create(settings);
            } else {
                mongoClient = MongoClients.create(connectionUri);
            }

            db = mongoClient.getDatabase(database);
            System.out.println("[MongoDBUtil] Connected to: " + database);
        } catch (Exception e) {
            throw new RuntimeException("[MongoDBUtil] Connection failed: " + e.getMessage(), e);
        }
    }

    /**
     * Finds all documents in a collection and returns them as a List of Maps.
     *
     * @param collectionName the collection to query
     * @return List of documents as Maps
     */
    public List<Map<String, String>> findAll(String collectionName) {
        return findDocuments(collectionName, null);
    }

    /**
     * Finds documents matching a filter.
     *
     * @param collectionName the collection to query
     * @param filter         BSON filter (use com.mongodb.client.model.Filters)
     * @return List of matching documents as Maps
     */
    public List<Map<String, String>> findDocuments(String collectionName, Bson filter) {
        List<Map<String, String>> results = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection(collectionName);

        FindIterable<Document> docs = (filter != null) ? collection.find(filter) : collection.find();

        for (Document doc : docs) {
            Map<String, String> row = new LinkedHashMap<>();
            for (String key : doc.keySet()) {
                Object value = doc.get(key);
                row.put(key, value != null ? value.toString() : null);
            }
            results.add(row);
        }
        return results;
    }

    /**
     * Finds documents and returns only the specified fields.
     *
     * @param collectionName the collection to query
     * @param filter         BSON filter (null for all documents)
     * @param fields         list of field names to include
     * @return List of documents with only the requested fields
     */
    public List<Map<String, String>> findDocuments(String collectionName, Bson filter, List<String> fields) {
        List<Map<String, String>> results = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection(collectionName);

        Document projection = new Document();
        for (String field : fields) {
            projection.append(field, 1);
        }
        projection.append("_id", 0);

        FindIterable<Document> docs = (filter != null)
                ? collection.find(filter).projection(projection)
                : collection.find().projection(projection);

        for (Document doc : docs) {
            Map<String, String> row = new LinkedHashMap<>();
            for (String key : doc.keySet()) {
                Object value = doc.get(key);
                row.put(key, value != null ? value.toString() : null);
            }
            results.add(row);
        }
        return results;
    }

    /**
     * Fetches a single field value from the first matching document.
     *
     * @param collectionName the collection to query
     * @param filter         BSON filter
     * @param fieldName      the field to extract
     * @return the value as String, or null if not found
     */
    public String fetchSingleValue(String collectionName, Bson filter, String fieldName) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        Document doc = (filter != null) ? collection.find(filter).first() : collection.find().first();
        if (doc != null && doc.get(fieldName) != null) {
            return doc.get(fieldName).toString();
        }
        return null;
    }

    /**
     * Fetches a single column/field from all matching documents.
     *
     * @param collectionName the collection to query
     * @param filter         BSON filter (null for all)
     * @param fieldName      the field to extract
     * @return list of values for that field
     */
    public List<String> fetchColumn(String collectionName, Bson filter, String fieldName) {
        List<String> values = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection(collectionName);

        FindIterable<Document> docs = (filter != null) ? collection.find(filter) : collection.find();
        for (Document doc : docs) {
            Object value = doc.get(fieldName);
            values.add(value != null ? value.toString() : null);
        }
        return values;
    }

    /**
     * Returns the count of documents matching a filter.
     *
     * @param collectionName the collection
     * @param filter         BSON filter (null for total count)
     * @return document count
     */
    public long getCount(String collectionName, Bson filter) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return (filter != null) ? collection.countDocuments(filter) : collection.countDocuments();
    }

    /**
     * Finds the first raw BSON Document matching a filter.
     * Use this for documents with nested structures (arrays, sub-documents).
     *
     * @param collectionName the collection to query
     * @param filter         BSON filter
     * @return the raw Document, or null if not found
     */
    public Document findRawDocument(String collectionName, Bson filter) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        return (filter != null) ? collection.find(filter).first() : collection.find().first();
    }

    /**
     * Finds all raw BSON Documents matching a filter.
     *
     * @param collectionName the collection to query
     * @param filter         BSON filter (null for all)
     * @return list of raw Documents
     */
    public List<Document> findRawDocuments(String collectionName, Bson filter) {
        List<Document> results = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection(collectionName);
        FindIterable<Document> docs = (filter != null) ? collection.find(filter) : collection.find();
        for (Document doc : docs) {
            results.add(doc);
        }
        return results;
    }

    /**
     * Closes the MongoDB connection.
     */
    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("[MongoDBUtil] Connection closed.");
        }
    }
}
