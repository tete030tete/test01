package com.ex.ex1.selenium.getrend;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//1부터 10까지의 합을 계산하는 두 개의 작업을 스레드풀에 처리 요청하고, 
//각각의 스레드가 작업 처리를 완료한 후 산출된 값을 외부 Result 객체에 누적하도록 했습니다.

public class ResultByCallableExam {
	 public static void main(String[] args) {
	        ExecutorService executorService = Executors.newFixedThreadPool(
	            Runtime.getRuntime().availableProcessors()
	        );
	        
	        System.out.println("작업 처리 요청 !");
	        
	        class Task implements Runnable {
	            Result result;
	                
	            Task(Result result) {
	                this.result = result;
	            }
	            
	            
	            @Override
	            public void run() {
	                int sum = 0;
	                
	                for (int i = 1; i <= 10; i++) {
	                    sum += i;
	                }
	                
	                result.addValue(sum);
	            }
	        };
	        
	        Result result = new Result();
	        Runnable task1 = new Task(result);
	        Runnable task2 = new Task(result);
	        Future<Result> future1 = executorService.submit(task1, result);
	        Future<Result> future2 = executorService.submit(task2, result);
	        
	        try {
	            result = future1.get();
	            result = future2.get();
	            System.out.println("처리 결과: " + result.accumValue);
	            System.out.println("처리 완료");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        executorService.shutdown();
	    }
	}
	 
	class Result {
	    int accumValue;
	    synchronized void addValue(int value) {
	        accumValue += value;
	    }
	}


