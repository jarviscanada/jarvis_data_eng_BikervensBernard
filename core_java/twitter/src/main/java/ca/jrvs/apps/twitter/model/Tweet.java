package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"
})
public class Tweet {

  @JsonProperty("created_at")
  private String created_at;

  @JsonProperty("id")
  private BigInteger id;

  @JsonProperty("id_str")
  private String idStr;

  @JsonProperty("text")
  private String text;

  @JsonProperty("entities")
  private Entities entities = null;

  @JsonProperty("coordinates")
  private Coordinates coordinates = null;

  @JsonProperty("retweet_count")
  private int retweetCount;

  @JsonProperty("favorite_count")
  private int favoriteCount;

  @JsonProperty("favorited")
  private Boolean favorited;

  @JsonProperty("retweeted")
  private Boolean retweeted;

  @JsonProperty("created_at")
  public String getCreated_at() {
    return created_at;
  }

  @JsonProperty("created_at")
  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  @JsonProperty("id")
  public BigInteger getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(BigInteger id) {
    this.id = id;
  }

  @JsonProperty("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("id_str")
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("entities")
  public Entities getEntities() {
    return entities;
  }

  @JsonProperty("entities")
  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(Coordinates c) {this.coordinates = c;}

  @JsonProperty("retweet_count")
  public int getRetweetCount() {
    return retweetCount;
  }

  @JsonProperty("retweet_count")
  public void setRetweetCount(int retweetCount) {
    this.retweetCount = retweetCount;
  }

  @JsonProperty("favorite_count")
  public int getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favorite_count")
  public void setFavoriteCount(int favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  @JsonProperty("favorited")
  public Boolean getFavorited() {
    return favorited;
  }

  @JsonProperty("favorited")
  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  @JsonProperty("retweeted")
  public Boolean getRetweeted() {
    return retweeted;
  }

  @JsonProperty("retweeted")
  public void setRetweeted(Boolean retweeted) {
    this.retweeted = retweeted;
  }
}
