package org.example.aspects;

public aspect FirstAspect {
    pointcut pc1(): execution(* org.example.test.Application.main(..));

//    before(): pc1() {
//        System.out.println("********** BEFORE MAIN **********");
//    }
//
//    after(): pc1() {
//        System.out.println("********** AFTER MAIN **********");
//    }

    void around(): pc1() {
        System.out.println("********** AROUND: BEFORE **********");

        // Call the actual method - THIS IS CRITICAL!
        proceed();

        System.out.println("********** AROUND: AFTER **********");
    }
}
