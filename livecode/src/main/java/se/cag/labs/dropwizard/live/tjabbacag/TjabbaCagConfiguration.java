package se.cag.labs.dropwizard.live.tjabbacag;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.dropwizard.Configuration;

class TjabbaCagConfiguration extends Configuration {
  @NotEmpty
  @JsonProperty
  private String template;

  @NotEmpty
  @JsonProperty
  private String defaultName = "Specialist";

  @NotEmpty
  @JsonProperty
  private String mongohost = "localhost";

  String getMongohost() {
    return mongohost;
  }

  Integer getMongoport() {
    return mongoport;
  }

  String getMongodb() {
    return mongodb;
  }

  @Min(1)
  @Max(65535)

  @JsonProperty
  private Integer mongoport = 27017;

  @NotEmpty
  @JsonProperty
  private String mongodb = "tjabba";

  String getTemplate() {
    return template;
  }

  String getDefaultName() {
    return defaultName;
  }

}
