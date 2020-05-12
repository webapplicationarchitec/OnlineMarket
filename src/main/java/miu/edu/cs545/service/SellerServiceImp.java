package miu.edu.cs545.service;

import miu.edu.cs545.domain.Seller;
import miu.edu.cs545.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SellerServiceImp implements SellerService {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImp(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller getByUsername(String username) {
        Optional<Seller> opt = sellerRepository.findById(username);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
}
