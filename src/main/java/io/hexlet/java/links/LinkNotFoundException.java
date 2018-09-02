package io.hexlet.java.links;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "link not found")
public class LinkNotFoundException extends RuntimeException {
}
