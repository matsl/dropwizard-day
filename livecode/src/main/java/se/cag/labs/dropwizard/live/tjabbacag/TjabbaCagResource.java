package se.cag.labs.dropwizard.live.tjabbacag;

import com.codahale.metrics.annotation.Timed;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/tjabba-cag")
@Produces(MediaType.APPLICATION_JSON)
public class TjabbaCagResource {
  private String template;
  private String defaultName;
  private AtomicLong counter = new AtomicLong();
  private JacksonDBCollection<Template, String> templates;

  TjabbaCagResource(String template, String defaultName, JacksonDBCollection<Template, String> templates) {
    this.template = template;
    this.defaultName = defaultName;
    this.templates = templates;
  }

  @GET
  @Timed
  public Greeting sägTjabba(@QueryParam("name") Optional<String> name) {
    String value;
    DBCursor<Template> dbCursor = templates.find();

    int count = dbCursor.count();

    if (count == 0) {
      value = String.format(template, name.orElse(defaultName));
    } else {
      Random random = new Random();
      dbCursor.skip(random.nextInt(count));
      Template currentTemplate = dbCursor.next();
      value = String.format(currentTemplate.getGreetingPhrase(), name.orElse(defaultName));
    }

    return new Greeting(counter.incrementAndGet(), value);
  }
}
