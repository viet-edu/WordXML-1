package vn.com.fsoft.service;

import vn.com.fsoft.dto.ConvertFormRequest;
import vn.com.fsoft.dto.ConvertFormResponse;

public interface ConvertService {
    ConvertFormResponse convert(ConvertFormRequest convertFormRequest) throws Exception;
}
