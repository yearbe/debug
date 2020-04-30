package app.z6_service_locator.service.impl;

import app.z6_service_locator.service.Service;

public class Service2 implements Service {
    @Override
    public void execute() {
        System.out.println("Executing Service2");
    }

    @Override
    public String getName() {
        return "Service2";
    }
}