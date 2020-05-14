package miu.edu.cs545.service;

import miu.edu.cs545.domain.*;
import miu.edu.cs545.exception.OrderCreateException;
import miu.edu.cs545.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderPagingRepository orderPagingRepository;
    private final ProductRepository productRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final BonusPointRepository bonusPointRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, OrderPagingRepository orderPagingRepository,
                           ProductRepository productRepository, BuyerRepository buyerRepository, SellerRepository sellerRepository, BonusPointRepository bonusPointRepository) {
        this.orderRepository = orderRepository;
        this.orderPagingRepository = orderPagingRepository;
        this.productRepository = productRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.bonusPointRepository = bonusPointRepository;
    }

    public void placeOrder(OnlineOrder order, String buyerId, Integer usedPoints) throws OrderCreateException {
        try {
            Buyer buyer = buyerRepository.findBuyerByUsername(buyerId);
            Seller seller = sellerRepository.findById(order.getSeller().getUsername()).get();

            OnlineOrder realOrder = new OnlineOrder();
            realOrder.setSeller(seller);
            realOrder.setBuyer(buyer);
            realOrder.setTax(order.getTax());
            realOrder.setTotal(order.getTotal());
            realOrder.setShippingFee(order.getShippingFee());
            realOrder.setShippingAddress(order.getShippingAddress());
            realOrder.setStatus(OrderStatus.New);
            Date currentDate = new Date();
            realOrder.setDateCreate(currentDate);
            realOrder.setDateShipping(getShippingDate(currentDate));
            realOrder.setOrderno("ORD_#" + "123");

            Integer point = 0;

            List<Integer> idList = new ArrayList<>();
            for (OrderDetail detail : order.getOrderDetailList()) {
                idList.add(detail.getProduct().getId());
            }

            Iterable<Product> products = productRepository.findAllById(idList);
            List<Product> productList = StreamSupport.stream(products.spliterator(), false)
                    .collect(Collectors.toList());

            String shippingAddress = order.getShippingAddress();
            if (shippingAddress == null || shippingAddress.equals("")) {
                shippingAddress = buyer.getBillingAddress().getStreet() + " "
                        + buyer.getBillingAddress().getCity() + " "
                        + buyer.getBillingAddress().getState() + ", "
                        + buyer.getBillingAddress().getZipcode();
            }
            realOrder.setShippingAddress(shippingAddress);

            List<OrderDetail> detailList = new ArrayList<>();
            for (OrderDetail detail : order.getOrderDetailList()) {
                OrderDetail realDetail = new OrderDetail();
                realDetail.setQty(detail.getQty());
                realDetail.setSellPrice(detail.getSellPrice());
                realDetail.setTotal(detail.getSellPrice() * detail.getQty());
                Product product = productList.stream().filter(p -> p.getId() == detail.getProduct().getId()).findFirst().get();
                realDetail.setProduct(product);
                detailList.add(realDetail);

                point += product.getPoint() * detail.getQty();
            }

            if (usedPoints != null && usedPoints > 0) {
                Optional<BonusPoint> optPoint = bonusPointRepository.findBySellerAndBuyer(seller, buyer);
                if (optPoint.isPresent()) {
                    BonusPoint bonusPoint = optPoint.get();
                    usedPoints = bonusPoint.getPoints() < usedPoints ? bonusPoint.getPoints() : usedPoints;
                    usedPoints = usedPoints > order.getTotal() ? (int) Math.ceil(order.getTotal()) : usedPoints;

                    bonusPoint.setPoints(bonusPoint.getPoints() - usedPoints);
                    bonusPointRepository.save(bonusPoint);
                    Double newTotal = realOrder.getTotal() - usedPoints;
                    newTotal = newTotal < 0 ? 0 : newTotal;
                    realOrder.setTotal(newTotal);
                }
            }

            realOrder.setPoint(point);
            realOrder.setOrderDetailList(detailList);
            realOrder.setUsedPoint(usedPoints);
            orderRepository.save(realOrder);
        } catch (Exception e) {
            throw new OrderCreateException("Could not create exception");
        }
    }

    public List<OnlineOrder> getAll() {
        return null;
    }

    public OnlineOrder getOrder(Integer id) {
        return null;
    }

    public OnlineOrder getOrder(String orderNo) {
        return null;
    }

    public OnlineOrder getOrderWithDetails(String orderNo) {
        return null;
    }

    public OnlineOrder getOrderWithDetails(Integer id) {
        return null;
    }

    @Override
    public Page<OnlineOrder> paging(String username,Pageable pageable) {
        Seller seller =new Seller();
        seller.setUsername(username);
        return orderPagingRepository.findOnlineOrdersBySeller(seller,pageable);
    }

    @Override

    public void updateStatus(OrderStatus status, Integer id) {

        if (status == OrderStatus.Delivered) orderRepository.updateDateDelivered(new Date(), id);
        else orderRepository.updateDateDelivered(null, id);

        orderRepository.updateStatus(status, id);

    }

    @Override
    public List<OnlineOrder> getByBuyer(String userName) {
        return orderRepository.getByBuyer(userName);
    }

    private Date getShippingDate(Date dateCreate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(dateCreate); // Now use today date.
        c.add(Calendar.DATE, 14); // Adding 5 days
        return c.getTime();
    }
}
