package miu.edu.cs545.service;

import miu.edu.cs545.domain.BonusPoint;
import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.Seller;

public interface BonusPointService {
    public BonusPoint getPoint(Seller seller, Buyer buyer);

    public BonusPoint savePoint(BonusPoint bonusPoint);
}
