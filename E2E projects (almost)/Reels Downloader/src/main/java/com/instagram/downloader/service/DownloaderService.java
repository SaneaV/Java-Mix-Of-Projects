package com.instagram.downloader.service;

import java.util.List;

public interface DownloaderService {

  void download(List<String> links);
}