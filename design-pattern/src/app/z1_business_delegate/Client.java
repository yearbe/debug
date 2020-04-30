package app.z1_business_delegate;

import app.z1_business_delegate.service.BusinessDelegate;

public class Client {

    BusinessDelegate businessService;

    public Client(BusinessDelegate businessService) {
        this.businessService = businessService;
    }

    public void doTask() {
        businessService.doTask();
    }
}