package com.instagram.downloader.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VideoInfo {
  @JsonProperty("url")
  private List<UrlInfo> urls;

  @Getter
  @Setter
  public static class UrlInfo {
    @JsonProperty("url")
    private String url;
  }
}