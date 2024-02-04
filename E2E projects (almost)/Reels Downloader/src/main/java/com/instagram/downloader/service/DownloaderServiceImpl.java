package com.instagram.downloader.service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DownloaderServiceImpl implements DownloaderService {

  private static final String FOLDER_PATH = "Video";
  private static final String FILE_FORMAT = ".mp4";

  private final WebClient webClient;
  private final String path;

  public DownloaderServiceImpl(WebClient webClient,
      @Value("${instagram.downloader.path}") String path) {
    this.webClient = webClient;
    this.path = path;
  }

  @Override
  public void download(List<String> links) {
    final URI uri = UriComponentsBuilder.fromUriString(path)
        .build().toUri();

    Flux.fromIterable(links)
        .delayElements(Duration.ofSeconds(30))
        .flatMap(link -> requestReelsData(uri, link))
        .doOnNext(this::handleSuccessRequest)
        .subscribe();
  }

  private Mono<VideoInfo> requestReelsData(URI uri, String link) {
    return webClient.post()
        .uri(uri)
        .bodyValue(new RequestBody(link))
        .retrieve()
        .bodyToMono(VideoInfo.class);
  }

  private void handleSuccessRequest(VideoInfo result) {
    final String urlDownloadable = result.getUrls().get(0).getUrl();

    download(urlDownloadable);
  }

  private void download(String urlDownloadable) {
    try {
      final URI uri = UriComponentsBuilder.fromUriString(urlDownloadable)
          .build(true)
          .toUri();

      webClient.get()
          .uri(uri)
          .retrieve()
          .bodyToMono(byte[].class)
          .doOnNext(this::saveFileLocally)
          .subscribe();

    } catch (Exception e) {
      System.err.println("Error downloading file: " + e.getMessage());
    }
  }

  private void saveFileLocally(byte[] content) {
    try {
      final String fileName = UUID.randomUUID() + FILE_FORMAT;
      final Path filePath = Paths.get(FOLDER_PATH, fileName);
      Files.write(filePath, content);
    } catch (IOException e) {
      System.err.println("Error saving file locally: " + e.getMessage());
    }
  }
}