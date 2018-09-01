package io.hexlet.java.links.resources;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Iterator;

import io.hexlet.java.links.LinkHelper;

@Path("links")
public class LinkResource {

    private static final String HOST = "localhost";

    private static final int PORT = 27017;

    private static final MongoCollection<Document> LINKS_COLLECTION;

    static {
        MongoClient client = new MongoClient(HOST, PORT);
        MongoDatabase db = client.getDatabase("jxr-rs");
        LINKS_COLLECTION = db.getCollection("links");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Response getUrlById(final @PathParam("id") String id) {
        if (id == null || id.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final FindIterable<Document> resultIterable = LINKS_COLLECTION.find(new Document("id", id));
        final Iterator<Document> resultIterator = resultIterable.iterator();
        if (!resultIterator.hasNext()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final String url = resultIterator.next().getString("url");
        if (url == null || url.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(url).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response shortUrl(final String url) {
        if (url == null || url.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        int attempt = 0;
        while (attempt < 5) {
            final String id = LinkHelper.getRandomId();
            final Document newShortDoc = new Document("id", id);
            newShortDoc.put("url", url);
            try {
                LINKS_COLLECTION.insertOne(newShortDoc);
                return Response.ok(id).build();
            } catch (MongoWriteException e) {
                System.out.println(e.getMessage());
            }
            attempt++;
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Response deleteUrlById(final @PathParam("id") String id) {
        if (id == null || id.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        DeleteResult result = LINKS_COLLECTION.deleteOne(new Document("id", id));
        if (!result.wasAcknowledged()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(result.getDeletedCount()).build();
    }
}
