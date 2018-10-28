package co.h2a.sandbox.springbootjndi.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        String sql = "select * from customer";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> row : rows) {
            Customer customer = new Customer();

            customer.setId((int) row.get("id"));
            customer.setFirstName((String) row.get("first_name"));
            customer.setLastName((String) row.get("last_name"));

            customers.add(customer);
        }

        return customers;
    }

}
