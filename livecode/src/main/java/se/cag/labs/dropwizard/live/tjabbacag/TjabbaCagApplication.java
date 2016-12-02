package se.cag.labs.dropwizard.live.tjabbacag;

import com.mongodb.DB;
import com.mongodb.Mongo;

import org.mongojack.JacksonDBCollection;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TjabbaCagApplication extends Application<TjabbaCagConfiguration> {

  public static void main(String... args) throws Exception {
    new TjabbaCagApplication().run(args);
  }

  @Override
  public String getName() {
    return "tjabba-cag";
  }

  @Override
  public void initialize(Bootstrap<TjabbaCagConfiguration> bootstrap) {
  }

  @Override
  public void run(TjabbaCagConfiguration configuration, Environment environment) throws Exception {
    Mongo mongo = new Mongo(configuration.getMongohost(), configuration.getMongoport());
    TjabbaCagMongoDbManaged managed = new TjabbaCagMongoDbManaged(mongo);
    environment.lifecycle().manage(managed);

    TjabbaCagHealthCheck healthCheck = new TjabbaCagHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("mongoDbHealthCheck", healthCheck);

    MongoDbHealthCheck mongoDbHealthCheck = new MongoDbHealthCheck(mongo);
    environment.healthChecks().register("template", mongoDbHealthCheck);

    DB db = mongo.getDB(configuration.getMongodb());
    JacksonDBCollection<Template, String> templates =
        JacksonDBCollection.wrap(db.getCollection("templates"),
                                 Template.class, String.class);

    TjabbaCagResource resource = new TjabbaCagResource(configuration.getTemplate(), configuration.getDefaultName(), templates);
    environment.jersey().register(resource);
  }
}
