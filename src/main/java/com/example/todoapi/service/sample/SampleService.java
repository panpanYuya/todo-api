package com.example.todoapi.service.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapi.repository.sample.SampleRecord;
import com.example.todoapi.repository.sample.SampleRepository;

//Bean登録を行う Di管理しているもの
@Service
public class SampleService {

  @Autowired
  private SampleRepository sampleRepository;

  public SampleEntity find() {
    var sampleRecord = sampleRepository.select();
    return new SampleEntity(sampleRecord.getContent());
  }

}
