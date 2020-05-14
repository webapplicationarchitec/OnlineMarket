package miu.edu.cs545.service;

import miu.edu.cs545.domain.OrderDetail;
import miu.edu.cs545.domain.Seller;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface OrderDetailService {
    public List<OrderDetail> getDetailByOrderId(Integer id);
    public String printReceipt(String format, Integer orderId) throws FileNotFoundException, JRException;
}
