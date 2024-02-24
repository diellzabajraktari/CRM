package crm.service.servicetype.impl;

import crm.service.servicetype.ServiceType;

public class SimCard implements ServiceType {
    @Override
    public String getServiceType() {
        return "SimCard";
    }
}
