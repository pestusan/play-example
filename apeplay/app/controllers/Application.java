package controllers;

import models.ToDo;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	String user = session("email");
    	Logger.info(user);
        return ok(index.render(ToDo.findTodosByUserEMail(user)));
    }

}
