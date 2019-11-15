package client.jobs;

import client.ExecutorContext;
import constant.PhaseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author create by Xiao Han 10/1/19
 * @version 1.0
 * @since jdk 1.8
 */
public class SkierWorker {

  private static Logger logger = LoggerFactory.getLogger(SkierWorker.class);

  private ExecutorService service;
  private int threads;
  private int timeStart;
  private int timeEnd;
  private CountDownLatch partialCD;
  private int totalThreads;
  private PhaseEnum phaseEnum;

  public SkierWorker(PhaseEnum phaseEnum, int threads, int totalThreads, int timeStart, int timeEnd, CountDownLatch countDownLatch) {
    this.threads = threads;
    this.service = Executors.newFixedThreadPool(threads);
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
    this.partialCD = countDownLatch;
    this.totalThreads = totalThreads;
    this.phaseEnum = phaseEnum;
  }

  public CountDownLatch run() {
    logger.info(phaseEnum.toString() + " start! Max requests " + totalThreads + " threads: " + threads);
    int requestPerThread = totalThreads / threads;
    int skierIDGap = ExecutorContext.numSkiers / threads;
    CountDownLatch taskCD = new CountDownLatch(threads);
    for (int i = 0; i < threads; i++) {
      service.execute(new SkierThread(i, requestPerThread, skierIDGap, timeStart, timeEnd, partialCD, phaseEnum, taskCD));
    }
    service.shutdown();
    return taskCD;
  }
}
