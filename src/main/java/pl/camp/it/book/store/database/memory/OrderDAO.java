package pl.camp.it.book.store.database.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.sequence.IOrderIdSequence;
import pl.camp.it.book.store.model.Order;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class OrderDAO implements IOrderDAO {

    @Autowired
    IOrderIdSequence orderIdSequence;

    private final List<Order> orders = new ArrayList<>();

    @Override
    public void persistOrder(Order order) {
        order.setId(this.orderIdSequence.getId());
        this.orders.add(order);


    }

    @Override
    public void updateOrder(Order order) {
        Iterator<Order> iterator = this.orders.iterator();
        while(iterator.hasNext()){
            Order orderFromDB = iterator.next();
            if(orderFromDB.getId() == order.getId()){
                iterator.remove();
                this.orders.add(order);
                break;
            }
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders){
            if(order.getUserId() == userId){
                result.add(order);
            }
        }
        return result;
    }
}
