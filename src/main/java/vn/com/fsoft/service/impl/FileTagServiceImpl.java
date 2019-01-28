package vn.com.fsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.fsoft.repository.FileTagRepository;
import vn.com.fsoft.service.FileTagService;

@Service
public class FileTagServiceImpl implements FileTagService {

    @Autowired
    private FileTagRepository fileTagRepository;
}
