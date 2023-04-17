package org.example;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class DockerApp {
    public static void main(String[] args) throws InterruptedException {
        OperatingSystemMXBean osMBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        System.out.println("availableProcessors:" + Runtime.getRuntime().availableProcessors());
        System.out.println("total physical memory, mb:" + osMBean.getTotalPhysicalMemorySize() / 1024 / 1024);
        System.out.println("totalMemory, mb:" + Runtime.getRuntime().totalMemory() / 1024 / 1024);
        System.out.println("maxMemory, mb:" + Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println("freeMemory, mb:" + Runtime.getRuntime().freeMemory() / 1024 / 1024);

        // Thread.sleep(TimeUnit.HOURS.toMillis(1));
        // Demonstration of Out Of Memory in container
        List<Long> list = new ArrayList<>();
        for (long idx = 0; idx < Long.MAX_VALUE; idx++) {
            Thread.sleep(1);
            list.add(System.currentTimeMillis());
            list.add(System.currentTimeMillis());
            list.add(System.currentTimeMillis());
        }
        System.out.println(list.size());

    }
}
