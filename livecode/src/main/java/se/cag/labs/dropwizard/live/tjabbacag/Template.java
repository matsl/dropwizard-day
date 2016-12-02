package se.cag.labs.dropwizard.live.tjabbacag;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.mongojack.ObjectId;

public class Template {
  @ObjectId
  @JsonProperty("_id")
  private String mongoId;

  private String language;

  private String greetingPhrase;

  public String getLanguage() {
    return language;
  }

  String getGreetingPhrase() {
    return greetingPhrase;
  }
}
