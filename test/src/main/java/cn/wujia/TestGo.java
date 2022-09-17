package cn.wujia;

import cn.wujia.io.Bio;
import cn.wujia.pool.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

//@SpringBootApplication
//开启sleuth
//@EnableDiscoveryClient


public class TestGo {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        // SpringApplication.run(TestGo.class, args);
        TestGo testGo = new TestGo();
        //  testGo.testScheduledExecutor();
        //  testGo.testTreeMap();
        //  Collections.synchronizedList(new ArrayList<>());
 /*       ThreadService user = new ThreadService();
        System.out.println(user.a);
        user.add();内部类测试
        System.out.println(user.a);*/
        //  Bio bio = new Bio();
        //  bio.test();
        List<String> a = new ArrayList();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        Iterator<String> c = a.iterator();

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).equals("1"))
                a.add("123");
        }
        for (String i : a) {
            System.out.println(i);
        }
    }


    public void treeSetTest() {
        TreeSet treeSet = new TreeSet();
        User user1 = new User(9, "123");
        User user2 = new User(4, "1234");
        User user3 = new User(7, "1235");
        treeSet.add(user1);
        treeSet.add(user2);
        treeSet.add(user3);
        for (Object a : treeSet) {
            User user = (User) a;
            System.out.println(user.toString());
        }
    }

    public void treeMapTest() {
        Map<Integer, User> treeMap = new TreeMap(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        User user1 = new User(9, "123");
        User user2 = new User(4, "1234");
        User user3 = new User(7, "1235");
        treeMap.put(user1.getId(), user1);
        treeMap.put(user2.getId(), user2);
        treeMap.put(user3.getId(), user3);
        for (Map.Entry<Integer, User> entry : treeMap.entrySet()) {
            Integer k = entry.getKey();
            User v = entry.getValue();
            System.out.println(v.toString());
        }
    }

    public Object callTest() throws ExecutionException, InterruptedException {
        User user = new User();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(user).get();
    }

    public void countDownLatchOrThreadTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("i:" + i);
                countDownLatch.countDown();
            }
        });
        thread.start();
        countDownLatch.await();
        System.out.println("i:");
    }

    public void countDownLatchOrExecutorTest() {
        ExecutorService exector = Executors.newFixedThreadPool(5);
        User user = new User();
        for (int i = 0; i < 10; i++) {
            exector.submit(user);
        }
        System.out.println("主线程");
        try {
            user.countDownLatch.await(500, TimeUnit.MILLISECONDS);//保证之前的所有的线程都执行完成，才会走下面的
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("超时");
        }
//        int index = 0;
//        while (user.count != 0 && user.a != 100000) {
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            index += 1;
//            if (index > 10) {
//                System.out.println("超时" + user.a);
//                return;
//            }
//        }
        System.out.println("主线程结束" + user.a + ",跑完了" + (int) (10 - user.countDownLatch.getCount()));
    }

    public void threadTest() {
        ThreadService threadService = new ThreadService();
        threadService.start();
    }

    public void cachedThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> {
            for (int a = 0; a > 10; a++) {
                final int index = a;
                System.out.println(a);
            }

        });
    }

    public void scheduledExecutorTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        AtomicInteger index = new AtomicInteger();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            index.getAndIncrement();
            System.out.println("第" + index);
        }, 10, 3, TimeUnit.SECONDS);
    }

    public void jDKorCGilbTest() {
        //  new User(new ThreadService()).show();//静态代理测试

        //创建被代理的实例对象
        ThreadService threadService = new ThreadService();
        //创建InvocationHandler对象
        InvocationHandler renterHandler = new ProxyFactory<ThreadService>(threadService);
        //创建代理对象,代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        ((Test) Proxy.newProxyInstance(
                Test.class.getClassLoader(),
                new Class<?>[]{Test.class},
                renterHandler)).show();//动态代理 基于invocationGandler接口实现

        ((ThreadService) new ProxyFactory(threadService).getProxyInstance()).add();//CGlib代理,

    }


}
