package info.atende.nserver.model;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giovanni Silva
 */
@Component
public class Counter {
    private AtomicInteger currentSendingCount = new AtomicInteger(0);
    private AtomicInteger totalSended = new AtomicInteger(0);
    private AtomicInteger totalFailed = new AtomicInteger(0);

    public int getCurrentValue() {
        return this.currentSendingCount.get();
    }

    public int incrementCurrentValue(){
        return this.currentSendingCount.incrementAndGet();
    }

    public int decrementCurrentValue(){
        return this.currentSendingCount.decrementAndGet();
    }

    public int incrementTotalSended(){
        return this.totalSended.incrementAndGet();
    }
    public int getTotalSended(){
        return this.totalSended.get();
    }

    public int incrementFailed(){
        return this.totalFailed.incrementAndGet();
    }

    public int getTotalFailed(){
        return this.totalFailed.get();
    }

    public Total getTotal(){
        return new Total(this.getCurrentValue(), this.getTotalSended(), this.getTotalFailed());
    }

    public static class Total {
        private final int currentSendingCount;
        private final int totalSendedCount;
        private final int totalFailedCount;
        private int threadPoolActiveCount;

        public Total(int currentSendingCount, int totalSendedCount, int totalFailedCount) {
            this.currentSendingCount = currentSendingCount;
            this.totalSendedCount = totalSendedCount;
            this.totalFailedCount = totalFailedCount;
        }

        public int getCurrentSendingCount() {
            return currentSendingCount;
        }

        public int getTotalSendedCount() {
            return totalSendedCount;
        }

        public int getTotalFailedCount() {
            return totalFailedCount;
        }

        public int getThreadPoolActiveCount() {
            return threadPoolActiveCount;
        }

        public void setThreadPoolActiveCount(int threadPoolActiveCount) {
            this.threadPoolActiveCount = threadPoolActiveCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Total total = (Total) o;

            if (currentSendingCount != total.currentSendingCount) return false;
            if (totalSendedCount != total.totalSendedCount) return false;
            return totalFailedCount == total.totalFailedCount;

        }

        @Override
        public int hashCode() {
            int result = currentSendingCount;
            result = 31 * result + totalSendedCount;
            result = 31 * result + totalFailedCount;
            return result;
        }
    }

}
