package com.pvapp.PVApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
    }
}
