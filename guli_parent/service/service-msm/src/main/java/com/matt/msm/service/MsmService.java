package com.matt.msm.service;

import java.util.Map;

public interface MsmService {
	  public boolean send(String PhoneNumbers, String templateCode, Map<String,Object> param);
}
