package crm.service.servicetype.impl;

import crm.service.servicetype.ServiceType;

public class Data implements ServiceType {

    @Override
    public String getServiceType() {
        return "Data";
    }
}
