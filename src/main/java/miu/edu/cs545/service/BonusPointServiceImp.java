package miu.edu.cs545.service;

import miu.edu.cs545.domain.BonusPoint;
import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.BonusPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BonusPointServiceImp implements BonusPointService {

    private final BonusPointRepository bonusPointRepository;

    @Autowired
    public BonusPointServiceImp(BonusPointRepository bonusPointRepository) {
        this.bonusPointRepository = bonusPointRepository;
    }

    @Override
    public BonusPoint getPoint(Seller seller, Buyer buyer) {
        Optional<BonusPoint> opt = bonusPointRepository.findBySellerAndBuyer(seller, buyer);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public BonusPoint savePoint(BonusPoint bonusPoint) {
        BonusPoint saved = bonusPointRepository.save(bonusPoint);
        return saved;
    }
}
