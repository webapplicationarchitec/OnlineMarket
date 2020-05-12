package miu.edu.cs545.service;

import miu.edu.cs545.domain.Buyer;

public interface BuyerService {
    public Buyer getByUsername(String username);
}
