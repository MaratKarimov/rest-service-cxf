package ru.javastudy.springMVC.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.javastudy.springMVC.impl.MessageService;
import ru.javastudy.springMVC.model.MessageModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageRest {

    @Autowired
    MessageService messageDao;

    public MessageRest() {
    }

    @GET
    @Path("/{id}")
    public @ResponseBody MessageModel getMessage(@PathParam("id") Integer id) {
        return messageDao.getMessageById(id);
    }

    @GET
    public @ResponseBody List<MessageModel> getMessages() {
        return messageDao.getMessages();
    }

    @POST
    public Response saveMessage(@ModelAttribute MessageModel model) {
        return Response.ok(messageDao.createMessage(model)).build();
    }
}
