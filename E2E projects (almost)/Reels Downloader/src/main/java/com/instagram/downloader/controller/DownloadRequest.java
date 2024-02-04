package com.instagram.downloader.controller;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DownloadRequest {

  private List<String> links;
}