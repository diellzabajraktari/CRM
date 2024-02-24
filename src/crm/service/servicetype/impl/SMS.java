package crm.service.servicetype.impl;

import crm.service.servicetype.ServiceType;

public class SMS implements ServiceType {
    @Override
    public String getServiceType() {
        return "SMS";
    }
}
