package crm.model;

import crm.service.servicetype.ServiceType;
import crm.service.servicetype.impl.SimCard;
import crm.service.servicetype.impl.Voice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Product {
    private final String name;
    private final double price;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;
    private final List<ServiceType> serviceTypes;
    private final List<Subscription> purchasedSubscribers;

    public Product(String name, double price, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.name = name;
        this.price = price;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.serviceTypes = new ArrayList<>();
        this.purchasedSubscribers = new ArrayList<>();
        serviceTypes.add(new SimCard());
        serviceTypes.add(new Voice());
    }

    public void addServiceType(ServiceType serviceType) {
        if(!serviceTypes.contains(serviceType)) {
            serviceTypes.add(serviceType);
        }
    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public boolean isAvailable() {
        LocalDateTime currentDate = LocalDateTime.now();
        return currentDate.isAfter(fromDateTime) && currentDate.isBefore(toDateTime);
    }

    public boolean isSubscriberAllowed(Subscription subscriber) {
        Set<Service> subscriberServices = subscriber.getServices();

        for (ServiceType includedServiceType : serviceTypes) {
            boolean serviceTypePresent = subscriberServices.stream()
                    .anyMatch(service -> service.getServiceType().equals(serviceTypes));

            if (!serviceTypePresent) {
                return false; // Subscriber is not allowed if any required service type is missing
            }
        }
        return true;
    }

    public void purchase(Subscription subscriber) {
        if (isAvailable() && isSubscriberAllowed(subscriber)) {
            purchasedSubscribers.add(subscriber);
            System.out.println("Product purchased successfully!");
        } else {
            System.out.println("Product not available or subscriber not allowed to use all ServiceTypes.");
        }
    }

    public List<Subscription> getPurchasedSubscribers() {
        return purchasedSubscribers;
    }
}
