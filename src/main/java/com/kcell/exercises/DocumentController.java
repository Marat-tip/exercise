package com.kcell.exercises;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents/")
public class DocumentController {

    @RequestMapping(method = RequestMethod.GET, value = "/last")
    public Document getLastDocument(){
        return new Document(100);
    }
}
