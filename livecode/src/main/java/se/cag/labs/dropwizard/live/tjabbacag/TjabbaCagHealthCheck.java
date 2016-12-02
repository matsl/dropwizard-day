package se.cag.labs.dropwizard.live.tjabbacag;

import com.codahale.metrics.health.HealthCheck;

public class TjabbaCagHealthCheck extends HealthCheck {
  private String template;

  TjabbaCagHealthCheck(String template) {
    this.template = template;
  }

  @Override
  protected Result check() throws Exception {
    String saying = String.format(template, "TEST");

    if (!saying.contains("TEST")) {
      return Result.unhealthy("Template does not include a name");
    }

    return Result.healthy();
  }
}
