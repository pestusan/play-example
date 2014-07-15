
import java.util.List;

import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;

import com.avaje.ebean.Ebean;


public class Global extends GlobalSettings{

	@Override
	public void onStart(Application app) {
		Logger.info("onStart");
		if(app.isDev() && User.count() == 0) {
			Logger.info("Bootstrapping with default data");
			int count = Ebean.save((List)Yaml.load("test-data.yml"));
			Logger.info("Count {}", count);
		}
	}
}
