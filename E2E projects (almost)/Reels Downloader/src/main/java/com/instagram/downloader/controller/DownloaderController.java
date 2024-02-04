package com.instagram.downloader.controller;

import com.instagram.downloader.service.DownloaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DownloaderController {

  private final DownloaderService downloaderService;

  @PostMapping(path = "/download")
  public void download(@RequestBody DownloadRequest request) {
    downloaderService.download(request.getLinks());
  }
}