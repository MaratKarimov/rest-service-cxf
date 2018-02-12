package ru.bva.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public class RootRest {
    public RootRest() {
    }

    @GET
    @Produces({"text/html"})
    public InputStream getMessage(@PathParam("id") Integer id) throws FileNotFoundException {
        File f = new File("C:\\JavaProjects\\SpringMVC_JSPHelloWorld\\web\\resources\\index.html");
        return new FileInputStream(f);
    }
}
