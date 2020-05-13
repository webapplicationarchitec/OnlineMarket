package miu.edu.cs545.service;

import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.Seller;

public interface BuyerService {
    public Buyer getByUsername(String username);
//    public Buyer updateFollower();
    public void save(Buyer buyer);
    public boolean isExistSeller(String sellerid);
}
