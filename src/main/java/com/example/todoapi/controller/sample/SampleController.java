package com.example.todoapi.controller.sample;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapi.service.sample.SampleEntity;
import com.example.todoapi.service.sample.SampleService;

//lombokで呼び出す。下記でフィールド変数のconstrucorを呼び出してくれる(autowairedと同じ)
// @RequiredArgsConstructor
@RestController
@RequestMapping("/samples")
public class SampleController {

  // constrocotrを呼び出してくれる
  @Autowired
  private SampleService service;
  // SampleService service = new SampleService();

  @GetMapping
  public SampleDto index() {
    var sampleEntity = service.find();
    return new SampleDto(sampleEntity.getContent(), LocalDateTime.now());
  }
}
