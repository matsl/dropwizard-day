package se.cag.labs.dropwizard.live.tjabbacag;

import com.mongodb.Mongo;

import io.dropwizard.lifecycle.Managed;

public class TjabbaCagMongoDbManaged implements Managed {
  private Mongo mongo;

  TjabbaCagMongoDbManaged(Mongo mongo) {
    this.mongo = mongo;
  }

  @Override
  public void start() throws Exception {

  }

  @Override
  public void stop() throws Exception {
    mongo.close();
  }
}
