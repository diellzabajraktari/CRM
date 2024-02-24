package crm.service.servicetype.impl;

import crm.service.servicetype.ServiceType;

public class Voice implements ServiceType {
    @Override
    public String getServiceType() {
        return "Voice";
    }
}
