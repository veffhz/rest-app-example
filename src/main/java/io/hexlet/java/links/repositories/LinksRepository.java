package io.hexlet.java.links.repositories;

import io.hexlet.java.links.Links;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinksRepository extends MongoRepository<Links, String> {

    Links findBy_id(ObjectId _id);

}
