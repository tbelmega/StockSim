package de.belmega.stocksim.trade;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.wildfly.common.Assert.assertTrue;

public class OrderTest {
    
    @Test
    public void testThatOrderMayBeABid() throws Exception {
        //arrange
        Bid bid = new Bid();

        //assert
        assertTrue(bid instanceof Order);
    }
    
    @Test
    public void testThatOrderMayBeAnAsk() throws Exception {
        //arrange
        Ask ask = new Ask();

        //assert
        assertTrue(ask instanceof Order);
    }
    
    @Test
    public void testThatOrderDoesNotExpireByDefault() throws Exception {
        //arrange
        Order order = new Bid();

        //act
    
        //assert
        assertThat(order.getExpiryDate().isPresent(), is(equalTo(false)));
    }

    @Test
    public void testThatOrderMayExpire() throws Exception {
        //arrange
        Order order = new Bid();
        LocalDateTime expiryDate = LocalDateTime.now();

        //act
        order.setExpiryDate(expiryDate);

        //assert
        assertThat(order.getExpiryDate().get(), is(equalTo(expiryDate)));
    }
}
