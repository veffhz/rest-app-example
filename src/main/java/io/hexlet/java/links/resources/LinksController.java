package io.hexlet.java.links.resources;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.hexlet.java.links.repositories.LinksRepository;
import io.hexlet.java.links.LinkHelper;
import io.hexlet.java.links.Links;


@RestController
@RequestMapping("/links")
public class LinksController {

    @Autowired
    private LinksRepository repository;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Links> getAllLinks() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Links getLinkById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyLinkById(@PathVariable("id") ObjectId id,
                               @Valid @RequestBody String url) {
        Links link = repository.findBy_id(id);
        link.setLongUrl(url);
        repository.save(link);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Links createLink(@Valid @RequestBody String url) {
        ObjectId _id = ObjectId.get();
        final String shortUrl = LinkHelper.getRandomId();
        Links links = new Links(_id, url, shortUrl, new Date());
        repository.save(links);
        return links;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLink(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }

}
