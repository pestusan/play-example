package controllers;

import models.ToDo;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(ToDo.findTodosByUserEMail("bob@example.com")));
    }

}
