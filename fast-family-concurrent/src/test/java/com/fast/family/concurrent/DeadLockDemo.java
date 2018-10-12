package com.fast.family.concurrent;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/12-14:31
 */
public class DeadLockDemo {

    private static String resource_a = "A";

    private static String resource_b = "B";


    public static void deadLock(){
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (resource_a){
                    System.out.println("get resource a");

                    try {
                        Thread.sleep(30000);
                        synchronized (resource_b){
                            System.out.println("get resource b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (resource_b){
                    System.out.println("get2 resource b");
                    synchronized (resource_a){
                        System.out.println("get2 resource a");
                    }
                }

            }
        });

        threadA.start();
        threadB.start();
    }



    public static void main(String[] args) {
        deadLock();
    }
}
