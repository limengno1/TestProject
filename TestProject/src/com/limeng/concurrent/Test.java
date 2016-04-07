package com.limeng.concurrent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws InterruptedException {
    	/*for (int i=0; i<1; i++) {
    		System.out.println(i);
    		testConcurrentModifyException();
    		Thread.sleep(100);
    	}*/
    	testConcurrentModifyException();
        
    }

	private static void testConcurrentModifyException() {
		User user1 = new User();
        user1.setId(1);
        user1.setName("zhangsan");

        User user2 = new User();
        user2.setId(2);
        user2.setName("lisi");

        Set userSet = new HashSet();
        userSet.add(user1);
        userSet.add(user2);
        for (Iterator it = userSet.iterator(); it.hasNext();) {
            User user = (User) it.next();
            if (user.getId() == 1) {
//                userSet.remove(user);
            	user.setName("lisi");
            }

            if (user.getId() == 2) {
                user.setName("zhangsan");
            }
        }
        /*for(Iterator it = userSet.iterator(); it.hasNext(); ) {
            User user = (User) it.next();
            System.out.println(user.getId() + "=>" + user.getName());
        }*/
	}
}
