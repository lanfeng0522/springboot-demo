package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.mapper.DemoMapper;
import com.example.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	DemoMapper demoMapper;
	@Autowired
	TransactionTemplate transactionTemplate;


	@Override
	public int transaction() {
		demoMapper.transaction2();
		
		transactionTemplate.execute(new TransactionCallback<Boolean>(){

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				demoMapper.transaction();
				int i=1/0;
				return null;
			}
			
			
		});
		return 0;
	}


	@Override
//	@Transactional
	public int transaction2() {
		demoMapper.transaction2();
		
		int i=1/0;
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){

			@Override
			public void doInTransactionWithoutResult(TransactionStatus status) {
				demoMapper.transaction();
			}
			
			
		});
		return 0;
	}
}
