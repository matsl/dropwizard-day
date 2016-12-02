package se.cag.labs.dropwizard.live.tjabbacag;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {
  @JsonProperty
  private Long id;

  @JsonProperty
  private String content;

  Greeting(Long id, String content) {
    this.id = id;
    this.content = content;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
