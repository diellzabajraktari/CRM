package crm;

import crm.model.Subscription;
import crm.service.TelecomService;

import java.util.List;

public class SubscriptionService implements TelecomService<Subscription> {


    @Override
    public void create(Subscription entity) {

    }

    @Override
    public void update(Subscription entity, int ID) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Subscription findById(int id) {
        return null;
    }

    @Override
    public List<Subscription> findAll() {
        return null;
    }
}
