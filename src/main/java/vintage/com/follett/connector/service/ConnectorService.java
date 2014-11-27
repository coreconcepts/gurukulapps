package com.follett.connector.service;

import com.follett.connector.util.DataBlock;
import com.follett.connector.util.StatusBean;

public interface ConnectorService {

	public StatusBean uploadData(DataBlock product);
		
		
}
