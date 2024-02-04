package com.instagram.downloader.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestBody {

  private final String url;
}