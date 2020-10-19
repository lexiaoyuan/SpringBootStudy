package com.springboot;

import com.springboot.pojo.Dog;
import com.springboot.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    private Dog dog;
    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(dog.toString());  // Dog{name='胖哈', age=2}
        System.out.println(person.toString());
        /*
        Person{
            name='lexiaoyuan',
            age=21,
            happy=true,
            birth=Thu Feb 11 00:00:00 CST 1999,
            maps={k1=v1, k2=v2},
            lists=[book, code, bug],
            dog=Dog{name='胖哈beta', age=1}
         }
         */
    }

}
