package org.example.customerservice.entities.projections;


import org.example.customerservice.entities.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "noAddress", types = Customer.class)
public interface CustomerProjection1 {

        String getId();
        String getName();
}
