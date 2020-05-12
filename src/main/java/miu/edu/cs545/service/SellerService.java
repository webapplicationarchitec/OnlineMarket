package miu.edu.cs545.service;

import miu.edu.cs545.domain.Seller;

public interface SellerService {
    public Seller getByUsername(String username);
}
