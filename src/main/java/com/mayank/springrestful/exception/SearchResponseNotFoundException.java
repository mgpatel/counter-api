package com.mayank.springrestful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Mayank on 26/06/16.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such text")
public class SearchResponseNotFoundException extends RuntimeException {
}
