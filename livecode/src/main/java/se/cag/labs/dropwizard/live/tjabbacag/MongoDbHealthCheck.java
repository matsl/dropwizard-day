package se.cag.labs.dropwizard.live.tjabbacag;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.Mongo;

public class MongoDbHealthCheck extends HealthCheck {
  private Mongo mongo;

  MongoDbHealthCheck(Mongo mongo) {
    this.mongo = mongo;
  }

  @Override
  protected Result check() throws Exception {
    return Result.healthy("Names:" + mongo.getDatabaseNames());
  }

}
