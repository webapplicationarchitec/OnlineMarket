package miu.edu.cs545.service;

import miu.edu.cs545.domain.Account;
import miu.edu.cs545.domain.Buyer;
import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BuyerServiceImp implements BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Buyer getByUsername(String username) {
        Optional<Buyer> opt = buyerRepository.findById(username);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public void save(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    @Override
    public boolean isExistSeller(String sellerid) {
        return buyerRepository.existsById(sellerid);
    }

//    @Override
//    public Buyer updateFollower(String username) {
//        return buyerRepository.saveAll(getByUsername());
//    }
}
