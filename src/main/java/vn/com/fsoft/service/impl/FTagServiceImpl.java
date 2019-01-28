package vn.com.fsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.fsoft.repository.FTagRepository;
import vn.com.fsoft.service.FTagService;

@Service
public class FTagServiceImpl implements FTagService {

    @Autowired
    private FTagRepository fTagRepository;
}
